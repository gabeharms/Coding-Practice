CONTAINER_NAME=rest-server
DEVELOPMENT_IMAGE_NAME=rest-server:1.0

default: build

clean: clean_docker_containers

setup: build_docker_image start_docker_container

run: run_application

build: 
	docker exec -it ${CONTAINER_NAME} mvn compile

build_docker_image:
	docker build -t ${DEVELOPMENT_IMAGE_NAME} .

start_docker_container:
	docker run -it -d -p 8080:8080 -m=4g -v `pwd`:/rest-server --name ${CONTAINER_NAME} ${DEVELOPMENT_IMAGE_NAME} /bin/bash

clean_docker_containers:
	docker ps -a | awk '{ print $$1,$$2 }' | grep ${DEVELOPMENT_IMAGE_NAME} | awk '{print $$1 }' | xargs -I {} docker stop {}
	docker ps -a | awk '{ print $$1,$$2 }' | grep ${DEVELOPMENT_IMAGE_NAME} | awk '{print $$1 }' | xargs -I {} docker rm {}

build_application:
	docker exec -it ${CONTAINER_NAME} mvn compile

run_application:
	docker exec -it ${CONTAINER_NAME} mvn spring-boot:run
