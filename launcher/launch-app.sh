#!/bin/bash

echo "launching app"
# clean

echo "clean up"

cd ..
docker-compose down

# build project (without tests, can't do the arquillian tests with the production build)

echo "building project"

mvn -Dmaven.test.skip=true package

cp target/AMT-project-01.war images/wildfly/data

# start dockers

echo "starting dockers"

docker-compose -f docker-compose.yml up --build
