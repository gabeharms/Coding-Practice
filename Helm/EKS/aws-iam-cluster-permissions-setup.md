### Summary

These instructions will help you setup an IAM user so that they can access the cluster and nodes
from the AWS console.

### Pre-requisites
- IAM user exists (cannot be root user)
- Cluster has been created already

### Instructions

First, edit the user and add the following permissions to them (inline is easist):
```
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "eks:DescribeNodegroup",
                "eks:ListNodegroups",
                "eks:DescribeCluster",
                "eks:ListClusters",
                "eks:AccessKubernetesApi",
                "ssm:GetParameter",
                "eks:ListUpdates",
                "eks:ListFargateProfiles",
                "eks:ListAddons",
                "eks:DescribeAddonVersions",
                "iam:ListRoles"
            ],
            "Resource": "*"
        }
    ]
}
```
The last step is to add the desired user to the aws-auth configmap. Within the aws-auth.yaml file 
provided in this project:
``  mapUsers: |
    - userarn: arn:aws:iam::066533940815:user/gabe
      username: gabe
      groups:
        - system:masters
``
modify the userarn and username sections to match your IAM user.

Lastly apply those aws-auth configmap changes to your cluster:
> kubectl apply -f aws-auth.yaml
