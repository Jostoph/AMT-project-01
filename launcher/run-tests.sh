#!/bin/bash

# build and start tests

echo "starting tests"

cd ..
mvn clean test

# clean

docker-compose down
