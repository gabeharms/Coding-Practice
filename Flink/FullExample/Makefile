
start:
	bin/start-cluster.sh

soft-restart:
	bin/soft-restart.sh
clean:
	bin/tear-down-cluster.sh

invoke_function:
	bin/invoke-function.sh

function_logs:
	bin/show-function-logs.sh

dns:
	bin/k8_dns.sh

build_charts:
	helm template statefun ./.charts/full-example --dry-run --debug --namespace statefun --values ./.charts/full-example/values.yaml