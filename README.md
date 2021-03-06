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

Don't forget to check our 404 custom page ;)

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
# from the launcher folder
./gen-data.sh
```

Now you can build and lauch the app by executing the `launcher/launch-app.sh` file.
```bash
# from the launcher folder
./launch-app.sh
```

To access the application, open a browser and go on the following link (change localhost by your docker-machine address if you use one)

[http://localhost:8080/AMT-project-01](http://localhost:8080/AMT-project-01)

## Run the tests suite

To run the tests suite you will first need to start the *test* dockers.
```bash
# from the launcher folder
./start-test-dockers.sh
```
Now you can start the tests (in another terminal)

It is possible that you will need to loggin to the wildfly administration page (on port 9990) before starting the tests with the credentials *admin* *admin*. It seems that it can cause some trouble to start the tests without doing that before.
```bash
# from the launcher folder
./run-tests.sh
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

Our sample data is generated using the **Change module** from **npm**, it has a fixed seed (42) so that it always generates the same data set.

The data consists of:

- 50'000 clients
- 1'000'000 products
- 74'998 orders
- 224'560 orderlines

## JMeter results

Using **JMeter**, we tried to quantify the impact of pagination in the **products servlet** when retrieving the products list from the database and diplaying them on the `/shop/products` page.

We configured **JMeter** to simulate 100 users/clients (threads) trying to access this page at the same time. The connection through the login page is puporsely not taken in account (tracked), it is not included in what we want to test.

The clients send 200 `GET` requests on the `/shop/products` page.

We start with a pagination of 10 records by pages, what gives us the following graph :

![GraphResponseTimeFor10](./Jmeter/GraphResponseTimeFor10.png)

 We can see that the response time stays between 17'000 and 20'000 milliseconds. 

 We rise the number of records by pages to 5'000 and restart the test to get the next graph : 

![GraphResoinseTimeFor5000](./Jmeter/GraphResoinseTimeFor5000.png)

The response time goes up between 33'000 and 39'000 milliseconds (after stabilisation).

We stoped the tests here because rising the number of elements started causing us trouble with the buffer of the server or the java memory. But at this point we can see that even with 5'000 elements/page the difference in terme of response time is already high enough to show the impact and utility of pagination.

We should also not forget that putting too few elements/page is not a great idea either. The users will need to perform more requests to go through the items (and on a user's point of view it can be tedious).

For the reasons listed above, we chose to use a pagination of 10 elements on our products page (It also looks better this way).

## Known bugs

There are actually no bugs in our application that we are awere of. If you find some, please think of it as a feature and contact us (It could be a feature that we don't really want ;)

## Features that could be improved (with additional time)

- It is actually not possible to create a product using the app, only manually adding it to the database. (we could make an additional page to let the client add a product, The DOAs have the methods ready for that.)
- It is not possible to buy a product directly from the page of the product (we could add a button and servlet logic to add it to the current shopping cart, it doesn't need a lot of changes)

