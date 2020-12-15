### Summary
This project shows you how to run a stateful application like
a database (MySQL) using the Kubernetes PersistentVolume and
and Deployment

### Prerequisites
* Install kubectl
* Install minikube

## Run the application

> minikube start

#### Deploy MySQL
You can run a stateful application by creating a Kubernetes Deployment 
and connecting it to an existing PersistentVolumne using a PersistentVolumneClaim.

For example, the mysql-deployment.yml file describes a Deployment that runs
MySQL and references the PersistentVolumnClaim. This file defines a volumne mount
for /var/lib/mysql, and then creates a PersistentVolumneClaim that looks for a 
20G volumne. This claim is satisified by an existing volume that meets the 
requirements.

> kubectl apply -f mysql-deployment.yml
> kubectl apply -f mysql-pv.yml

view the deployment
> kubectl describe deployment mysql

list the pods:
> kubectl get pods -l app=mysql

inspect the persistent volumne:
> kubectl describe pvc mysql-pv-claim


#### Accessing the MySQL instance

The previous deployment yml file creates a service that allows other Pods in the cluster 
access the database. The Service option "clusterIP: None" lets the Service DNS name resolve
directly to the Pod's IP address. This is optimial when you have only one Pod behind a Service 
and you don't intend to increase the number of Pods.


Run a MySQL client to connect to the server:

> kubectl run -it --rm --image=mysql:5.6 --restart=Never mysql-client -- mysql -h mysql -ppassword

This command creates a new pod in the cluster running a MySQL client and connects to it
through the Service. The "-h mysql" represents dns resolution that the Kubernetes cluster
automatically handles for us.  This is because the Service we created has a name of "mysql".


