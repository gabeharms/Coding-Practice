
# Provision new minikube cluster
minikube config set memory 5120
minikube start
minikube ssh 'sudo ip link set docker0 promisc on'
eval $(minikube -p minikube docker-env)


# Apply charts
kubectl create -f 00-namespace
kubectl config set-context --current --namespace statefun
kubectl create -f 01-minio
kubectl create -f 02-kafka
(cd 03-functions; make image)
(cd 03-functions; make service)
kubectl create -f 04-statefun

# Wait for pods to come up
echo "-----------------\nWaiting for pods to be ready:\n"
kubectl wait -n statefun --all --for=condition=Ready pod

# Port forward for Flink UI
kubectl port-forward svc/statefun-master-rest 8081:8081 -n statefun &