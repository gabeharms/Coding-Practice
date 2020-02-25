include .env
export

default: build

clean: clean_docker_containers

setup: build_docker_image start_docker_container

run: run_application

build: 
	docker exec -it ${CONTAINER_NAME} mvn compile

build_docker_image:
	docker build -t ${DEVELOPMENT_IMAGE_NAME} .
	docker build -t ${POSTGRES_IMAGE_NAME} -f Dockerfile.postgres .

start_docker_container:
	echo ${POSTGRES_PASSWORD}
	docker run -d -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} --name pg_container ${POSTGRES_IMAGE_NAME}
	docker run -it -d -p 8080:8080 -m=4g -v `pwd`:/server-with-postgres --link=pg_container:database --name ${CONTAINER_NAME} ${DEVELOPMENT_IMAGE_NAME} /bin/bash

clean_docker_containers:
	docker ps -a | awk '{ print $$1,$$2 }' | grep ${DEVELOPMENT_IMAGE_NAME} | awk '{print $$1 }' | xargs -I {} docker stop {}
	docker ps -a | awk '{ print $$1,$$NF }' | grep pg_container  | awk '{print $$1 }' | xargs -I {} docker stop {}
	docker ps -a | awk '{ print $$1,$$2 }' | grep ${DEVELOPMENT_IMAGE_NAME} | awk '{print $$1 }' | xargs -I {} docker rm {}
	docker ps -a | awk '{ print $$1,$$NF }' | grep pg_container | awk '{print $$1 }' | xargs -I {} docker rm {}

build_application:
	docker exec -it ${CONTAINER_NAME} mvn compile

run_application:
	docker exec -it ${CONTAINER_NAME} mvn spring-boot:run
