default: run

run:
	docker-compose up

run_debug:
	JVM_ARGS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=*:30005" docker-compose up

tests:
	docker-compose run web mvn test

tests_debug:
	docker-compose run -p 30006:30006 web mvn -Dmaven.surefire.debug="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=*:30006" test

build:
	docker-compose run web mvn compile

build_production:
	docker-compose run web mvn package
	docker build --tag projectName:latest -f Dockerfile.prod .

lint_dockerfile:
	find . -name Dockerfile\* | xargs -I{} bash -c "echo '{}' && [[ -f '{}' ]] && docker run -i hadolint/hadolint:v1.17.5-8-gc8bf307-alpine < '{}'" -- {}

clean:
	docker-compose run web mvn clean
	docker-compose rm --stop --force web
	docker-compose down --rmi all
