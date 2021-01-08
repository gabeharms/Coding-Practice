### Summary

The following doc outlines how to leverage helm with AWS EKS service.


### Pre-requisites

- helm CLI installed
- eksctl CLI installed
- kubectl CLI installed
- In the hello-world-server/values.yaml, ensure that ingress: enabled is set to false


### Setup

Configure your AWS credentials ([create new key](https://docs.aws.amazon.com/IAM/latest/UserGuide/id_credentials_access-keys.html#Using_CreateAccessKey)) :
> export AWS_ACCESS_KEY_ID=<key>
> export AWS_Secret_KEY_ID=<secret>


Create your cluster:
> eksctl create cluster

This will create a Kubernetes cluster in the EKS console. It also automatically adds teh proper
configurations to the ~/.kube/config file and sets this new cluster as your current cluster.

This allows you to inspect the nodes in your cluster:
> kubectl get pods

Instead of creating a cluster with all defaults, you can also create a cluster by defining a
kubernetes manifest file (this is placed in cluster.yaml of this project).

First delete the old cluster:
> eksctl delete cluster --name <name-of-cluster>

Then create a new one using the yaml Kubernetes manifest:
> eksctl create cluster -f cluster.yaml

(this cluster can then be deleted as so: eksctl delete cluster -f cluster.yaml)

You can then inspect the nodes of the cluster:
> kubectl get nodes

This should list all the nodes created in the cluster.

If you would like to have a user be able to see all the nodes within a cluster from the AWS web UI
console, follow the steps in aws-iam-cluster-permissions-setup.md


Now that the cluster has been created, lets run some applications on it.

To start, lets first use helm to apply the Kubernetes manifests from the custom hello-world-server
helm chart. This helm chart was created using 'helm create hello-world-server'

Out of the box, our chart has a service, deployment, test, ingress and values.yaml created for us.
The default chart has a deployment with the image configurable. So using the values.yaml, we have
simply are simply going to instruct the deployment to contain nginx images.

With the values.yaml already properly configured, and the current-context of kubectl already set
to our cluster, all we have to do is install the chart using the values.yaml:
> helm install -f hello-world-server/values.yaml hello-world-server ./hello-world-server

This installs a new chart named 'hello-world-server', pulling in values from values.yaml and templates
from the ./hello-world-server directory.

Helm makes APIs calls to the cluster and now you should have all the resources defined in the chart
in your cluster:

> kubectl get pods
> kubectl get deployments
> kubectl get services


You can also see that your chart is installed on the cluster:
> helm ls

This command also shows you the revision number which indicates the number of updates that have been
made to this chart. This is useful for using the rollback feature that helm provides, allowing you
to revert a chart to any previous revision:
> helm rollback hello-world-server 1

Now that your chart is now running on the cluster. From here on, when you want to make changes to the chart
and deploy them, you won't use the 'install' command anymore, but rather the 'upgrade':
> helm upgrade -f hello-world-server/values.yaml hello-world-server ./hello-world-server

Any changes to your templates or values.yaml in the hello-world-server chart will be sent up to the
cluster.


With your hello-world-server running, you can now run the test in our helm chart. This test found
in './hello-world-server/templates/tests/test-connection.yaml' is configured to spin up a pod and
run a single command and then terminate. This test pod will be deployed within the kubernetes cluster
and thus be able to leverage the Kubernetes DNS server and call the hello-world-server by name.

Since our service resource is of type ClusterIP, it is only accessible from within the Kubernetes
cluster, if it were of type NodePort, or if we configured the Ingress resource, then we would be
able to ping the service from outside the cluster. However, since its not, this test is very handy.

This test can be run as follows:
> helm test hello-world-server

After this completes successfully, you should see an extra pod in your cluster with the status of
completed:
> kubectl get pods

You should also see the output of a wget command examining its logs:
> kubectl logs hello-world-server-test-connection

If you examine the logs from the pod running nginx, you will also see that it receives this request
from your test pod:

> kubectl logs <pod-name>

```
192.168.8.37 - - [06/Jan/2021:00:17:58 +0000] "GET / HTTP/1.1" 200 612 "-" "kube-probe/1.18+" "-"
192.168.8.37 - - [06/Jan/2021:00:18:01 +0000] "GET / HTTP/1.1" 200 612 "-" "kube-probe/1.18+" "-"
192.168.36.100 - - [06/Jan/2021:00:18:01 +0000] "GET / HTTP/1.1" 200 612 "-" "Wget" "-"
```

You will also find a long trail of heartbeat requests from the Kubernetes cluster making sure that
the pod is still healthy.




### Ingress and Service (Kind: NodePort)

Ingress resources and Service resources of kind NodePort exist to allow external requests come into
the cluster and be routed to a particular service and eventually pods.

When deploying these resources to a Kubernetes cluster in a cloud service (i.e. AWS) they translate
into AWS load balancer resources. Since these AWS resources are not associated w/ EKS or the cluster,
special configuration is required in order for the cluster to be able to create and configure these
resources.

In most cases, it involves having some kind of controller or operator run in your cluster that exists
specifically to find ingress or service Kubernetes resources and translate them into AWS load balances
and configure them to route into your cluster.

The mappings are as follows:
- Ingress -> AWS Application Load Balancer (ALB)
- Service -> AWS Network Load Balancer (NLB)

The following guide shows you how to:
- create a policy and service account (provides the controller with the proper permissions to create ALB/NLB objects)
- install the controller to your cluster

https://kubernetes-sigs.github.io/aws-load-balancer-controller/latest/deploy/installation/

#### Detailed run through of above tutorial

Setup OIDC Provider:
> eksctl utils associate-iam-oidc-provider --region us-west-2 --cluster this-is-my-cluster --approve

Download IAM policy for the AWS Load Balancer Controller:
> curl -o iam-policy.json https://raw.githubusercontent.com/kubernetes-sigs/aws-load-balancer-controller/v2.1.0/docs/install/iam_policy.json

Create a new IAM policy:
> aws iam create-policy --policy-name AWSLoadBalancerControllerIAMPolicy --policy-document file://iam-policy.json

and you should get back something like the following:
``
{
    "Policy": {
        "PolicyName": "AWSLoadBalancerControllerIAMPolicy",
        "PolicyId": "ANPAQ67N3LZHRBNATWSOA",
        "Arn": "<arn-was-here> ",
        "Path": "/",
        "DefaultVersionId": "v1",
        "AttachmentCount": 0,
        "PermissionsBoundaryUsageCount": 0,
        "IsAttachable": true,
        "CreateDate": "2021-01-06T14:56:51+00:00",
        "UpdateDate": "2021-01-06T14:56:51+00:00"
    }
}``

Now create a IAM role and ServiceAccount for the AWS Load Balancer Controller, using the ARN from
above:
> eksctl create iamserviceaccount --cluster=this-is-my-cluster --namespace=kube-system --name=aws-load-balancer-controller --attach-policy-arn=arn:aws:iam::<AWS_ACCOUNT_ID>:policy/AWSLoadBalancerControllerIAMPolicy --approve

NOTE: When installing helm later, the aws-load-balancer-controller deployment fails to create pods
because the pod cannot find the service account becasue its looking in the default namesapce. Since
I don't know how to make the pod look in the correct namespace, i simply removed the --namespace
flag from the above command and the service account is then in the default namespace and can be 
looked up.


Add the EKS chart repo to helm:
> helm repo add eks https://aws.github.io/eks-charts

Apply the TargetGroupBinding CRDs to your cluster:
> kubectl apply -k "github.com/aws/eks-charts/stable/aws-load-balancer-controller//crds?ref=master"

This CRD allows your controller that will be installed next, to update custom resources in the 
cluster.

Now install the aws load balancer controller to your cluster:
> helm upgrade -i aws-load-balancer-controller eks/aws-load-balancer-controller --set clusterName=this-is-my-cluster --set serviceAccount.create=false --set serviceAccount.name=aws-load-balancer-controller


### NEXT: Learn how to configure external DNS w/ EKS

https://www.freecodecamp.org/news/how-to-setup-dns-for-a-website-using-kubernetes-eks-and-nginx/
