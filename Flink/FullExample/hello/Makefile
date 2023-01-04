.PHONY: configmap image service

image:
	export $(shell minikube docker-env)
	docker build . -t hello
