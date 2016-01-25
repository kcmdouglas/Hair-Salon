#Hair Salon
#### Database set-up for a hair salon
#### January 21, 2016.

#### By Kassidy Douglas

## Description

This repository is a code review for week three of Java. In it, a stylist owner can add new stylists, new clients for specific stylists, as well as delete and update information.

## Setup/Installation Requirements

* Be sure to have all technologies used installed.
* Clone this repository
* Run a server in a terminal, using
`postgres`
* In a separate terminal, run:
`psql`
* In PSQL, run these commands:
```CREATE DATABASE hair_salon;
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
CREATE TABLE clients (id serial PRIMARY KEY, name varchar, stylist_id int);
CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;
```

* Download this repository
* In a separate terminal, navigate to your project folder then run:
```psql hair_salon < hair_salon.sql
```

* Finally, in the same terminal in which you are viewing your project folder, run the command:
  ```gradle run
  ```



## Technologies Used

Java, Spark, JUnit, Velocity, Bootstrap, FluentLenium, Postgres and Sql2o
