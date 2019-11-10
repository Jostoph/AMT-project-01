# Teaching-HEIGVD-AMT-2019-Project-One
## Objectives

The main objective of this project is to apply the patterns and techniques presented during the lectures, and to create a simple multi-tiered application in Java EE.

## prerequisites
we consider you have install correctly install all of this programmer:
 - [node](https://nodejs.org/en/download/)
 - [docker & docker-compose](https://www.docker.com/get-started)
 - [git](https://www.atlassian.com/git/tutorials/install-git)
you also need to have an account on [github](https://github.com/join?source=header-home) and a functional [ssh protocol to gitHub](https://help.github.com/en/github/authenticating-to-github/connecting-to-github-with-ssh) 
## installation 
### free you're port and memory
this is optional but can avoid some bug like `port already use`or` not enouth space` 
run this command in a terminal when the daemon docker is started 
```shell
# kill all running docker (can avoid port already use) : 
$ docker kill $(docker ps -q)
# remove images (can avoid not enouth space):
$ docker images -aq -f 'dangling=true' | xargs docker rmi
# remove volumes (can avoid not enouth space):
$ docker volume ls -q -f 'dangling=true' | xargs docker volume rm
```
for the two last command you can use with caution --force
### run project 
the next command set up the project
```shell
# go cd where you wanna put the file of the project
$ cd path/where/you/wanna
# clone the repository
~path/where/you/wanna/ $ git clone git@github.com:Jostoph/AMT-project-01.git
# go to project 
~path/where/you/wanna/ $ cd AMT-project-01

# generate the data 
~path/where/you/wanna/AMT-project-01/ $ cd datagenerator
# install the package 
~path/where/you/wanna/AMT-project-01/datagenerator/ $ npm install
# run the script
~path/where/you/wanna/AMT-project-01/datagenerator/ $ node generator.js

# go back to root of the project when the script is finish
~path/where/you/wanna/AMT-project-01/datagenerator/ $ cd .. 
# launch the docker (you need a daemon docker running in you're computer)
~path/where/you/wanna/AMT-project-01/ $ docker-compose up --build
```
at this steep you have the project running on you're computer you can access by using a browser with the url localhost:9990 or docker-url:9990 depend on you're docker set up. 

### some information about this installation

#### about the data generator

we have seed the data generator so we will have all the time the same data event if you re-execute the node script

