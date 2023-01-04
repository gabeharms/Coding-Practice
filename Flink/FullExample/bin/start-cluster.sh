
# Provision new minikube cluster
minikube config set memory 5120
minikube start
minikube ssh 'sudo ip link set docker0 promisc on'
eval $(minikube -p minikube docker-env)


(cd hello; make image)

helm install statefun --namespace statefun --create-namespace ./.charts/full-example

sleep 5

# Wait for pods to come up
echo "-----------------\nWaiting for pods to be ready:\n"
kubectl wait -n statefun --all --for=condition=Ready --timeout=600s pod

# Port forward for Flink UI
kubectl port-forward svc/statefun-master-rest 8081:8081 -n statefun &

say -v "Tessa" "Cluster is ready"