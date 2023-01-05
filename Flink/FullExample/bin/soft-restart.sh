
# Provision new minikube cluster
eval $(minikube -p minikube docker-env)

kubectl delete namespace statefun

(cd hello; make image)

helm install statefun --namespace statefun --create-namespace ./.charts/full-example

kubectl wait -n statefun --all --for=delete --timeout=600s pod
sleep 5

# Wait for pods to come up
echo "-----------------\nWaiting for pods to be ready:\n"
kubectl wait -n statefun --all --for=condition=Ready --timeout=600s pod

# Port forward for Flink UI
kubectl port-forward svc/statefun-master-rest 8081:8081 -n statefun &

say -v "Tessa" "Cluster has been soft restarted"