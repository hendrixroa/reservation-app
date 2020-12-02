.PHONY: build build_docker
################################################################################
# DEVELOP
#
# make dev_api
#
################################################################################
dev_api:
	cd api && export $(cat .env | xargs) && mvn spring-boot:run

build_api_docker:
	docker build -t api:latest --build-arg APP="api" . -q

build_docker_app:
	docker build -t api:latest --build-arg APP="api" .
	APP=api) docker-compose up --force-recreate

