# Teaching-HEIGVD-AMT-2019-Project-One

## Objectives

The main objective of this project is to apply the patterns and techniques presented during the lectures, and to create a simple multi-tiered application in Java EE.

## The Black Market

Our application is called **The Black Market**, it is a simplified online shop (extremely simplified)
that is accessible by creating an account on it. After you are logged in you can start browsing through a
set of *fake products*, and them to your shopping cart and order (or cancel) them. Nothing to pay, but you wont get them anyway ;)

You can take a look at your account and see your order history. There you have the possibility to delete your account or
go on the **Account Edition page** to change your **email** or your **password**.

When you find a product look closer at some of the products you can click on the **see product** link and
it will show you the specific product on a new page with some more information and an image.

## Pre-requisites

we consider you have install correctly install all of this programs:
 - [node](https://nodejs.org/en/download/)
 - [docker & docker-compose](https://www.docker.com/get-started)
 
## Recommended

 - [git](https://www.atlassian.com/git/tutorials/install-git) (This way you can *pull* the project instead of downloading it)
 - An **internet connection** when using the application. We are using some CDNs to make our pages looking nice and
 the images of our products are also taken from a remote site. If you are offline it will be pretty sad.
 
## Installation

Download or *pull* the project.

### Configuration

If you only want to launch the application there is nothing much to do and you can skip to the **Run project** part.
But if you want to launch the test suite you will need to configure the `arquillian.xml` file in `src/test/resources/arquillian.xml` 
with either `localhost` or `<your docker-machine ip>` in the following places according to your installation.

```xml
<property name="managementAddress">localhost</property>
```

```xml
<property name="host">localhost</property>
```


## Run app

All the executables are in the `launcher` folder.

First, generate the sample data by executing the `launcher/gen-data.sh` file. (you only need to do that once ! It takes some time...)
```bash
# from the project root folder
./launcher/gen-data.sh
```

Now you can build and lauch the app by executing the `launcher/launch-app.sh` file.
```bash
# from the project root folder
./launcher/launch-app.sh
```

To access the application, open a browser and go on the following link (change localhost by your docker-machine address if you use one)

[http://localhost:8080/AMT-project-01](http://localhost:8080/AMT-project-01)

## Run the tests suite

To run the tests suite you will first need to start the *test* dockers.
```bash
# from the project root folder
./launcher/start-test-dockers.sh
```
Now you can start the tests (in another terminal)
```bash
# from the project root folder
./launcher/run-tests.sh
```

## Troubleshooting

- `port already use` or` not enouth space`

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
    
- Error while launching the tests suite

   Make sure you have set your `arquillian.xml` with *localhost* or with your **docker-machine** address.

- Launching `launch-app.sh` fails with `not found (Errcode: 2 - No such file or directory)`

  Make sure you generated the sample data first (at least once). 
  
  See [**Run App**](#run-app)
    

## Some additional information

### Sample data generation

// TODO


