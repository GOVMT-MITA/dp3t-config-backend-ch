######################
#      Makefile      #
######################

RUSTY_SWAGGER = rusty-swagger

all: clean all1
all1: clean updateproject
no: clean updateproject updatedoc swagger
docker-build: updateproject docker
doc: updatedoc swagger

updateproject:
	mvn -f dpppt-config-backend/pom.xml install -DskipTests
updatedoc:
	mvn springboot-swagger-3:springboot-swagger-3 -f dpppt-config-backend/pom.xml
	cp dpppt-config-backend/generated/swagger/swagger.yaml documentation/yaml/sdk.yaml

swagger:
	cd documentation; $(RUSTY_SWAGGER) --file ../dpppt-backend-sdk//generated/swagger/swagger.yaml

docker:
	cp dpppt-config-backend/target/dpppt-config-backend.jar ws-config/ws/bin/
	docker build -t peppptdweacr.azurecr.io/dpppt-mt-config:latest ws-config/

clean:
	@rm -f documentation/*.log documentation/*.aux documentation/*.dvi documentation/*.ps documentation/*.blg documentation/*.bbl documentation/*.out documentation/*.bcf documentation/*.run.xml documentation/*.fdb_latexmk documentation/*.fls documentation/*.toc
