#!/bin/bash

echo "launchin test suite"
# clean

cd ..
docker-compose down

# start dockers

echo "starting dockers"

docker-compose -f docker-compose-dev.yml up --build
