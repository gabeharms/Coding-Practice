### Summary
This quick tutorial uses prebuilt images and creates kubernetes objects from the command 
line, so there are no accompanying files. The rest of this README will outline the tutorial


### Prerequisites
* Install kubectl
* Install minikube


## Tutorial

Start the minikube cluster:
> minikube start

Open minikube dashboard:
> minikube dashboard

This will provide a UI to inspect the different aspects of the kubernetes cluster
that minikube has started for you.


#### Create a Deployment
A Kubernetes Pod is a group of one or more Containers, tied together for the purposes of 
administration and networking. The Pod in this tutorial has only one Container. A Kubernetes
Deployment checks on the health of your Pod and restarts the Pod's Container if it terminates. 
Deployments are the recommended way to manage the creation and scaling of Pods

> kubectl create deployment hello-node --image=k8s.gcr.io/echoserver:1.4

view the deployment

> kubectl get deployments

view the pod
> kubectl get pods

view the cluster's events
> kubectl get events

view the kubectl configuration
> kubectl config view


#### Create a Service

By default the Pod is only accessible by its internal IP address within the Kubernetes cluster. 
To make the  hello-node Container accessible from outside the Kubernetes virtual network,
you have to expose the Pod as a Kubernetes Service.

Expose the POD to the public internet:
> kubectl expose deployment hello-node --type=LoadBalancer --port=8080

view the new service:
> kubectl get services

On cloud providers that support load balancers, an external IP address would be provisioned to 
access the Service. On minikube, the LoadBalancer type makes the Service accessbile through the
"minikube service" command.

> minikube service hello-node


#### Clean up

Now we can clean up the resources we created in the cluster:
> kubectl delete service hello-node
> kubectl delete deployment hello-node
> minikube stop
> minikube delete
