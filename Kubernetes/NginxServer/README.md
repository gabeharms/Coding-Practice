### Summary
This project lets you create and scale an Nginx server


## Run the cluster

> minikube start

Create the deployment:
> kubectl apply -f nginx-deployment.yml

Inspect deployment:
> kubectl describe deployment nginx-deployment

List the Pods created by the deployment:
> kubectl get pods -l app=nginx

Display information about a pod
> kubectl describe pod <pod-name>


### Update the Deployment

Update the version of the image from 1.14.2 to 1.16.1 in the
deployment yml file. Apply it:
> kubectl apply -f nginx-deployment.yml

Watch the deployment create pods with new names and delete the old ones:
> kubectl get pods -l app=nginx


### Scale the deployment

Modify the "replicas" key in the deployment yml from 2 to 4 and re-apply:
> kubectl apply -f nginx-deployment.yml

Watch the deployment create more pods:
> kubectl get pods -l app=nginx


### Destroy the deployment
> kubectl delete deployment nginx-deployment
