# Architecture of the Program

## Structure

The program uses a three-tier architecture, and the package structure of the code is following:


<img src="https://github.com/sainioan/gitRep/blob/master/pictures/Structure.png">

## Graphical User Interface

The GUI consistis of three scenese:
- login
- sign up 
- MyBudgetApp scene

## Application Logic

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/Sovelluslogiikka.png">


### Class/Package Diagram
<img src="https://github.com/sainioan/gitRep/blob/master/pictures/class diagram.png">

## Data storage

The application follows the  [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) design pattern and stores data in an sql ddtabase.

## Database

Data about the users and their financial transactions is saved into mybudgetapp.db, which is an SQLite database. 
The database creates the database 

CREATE TABLE IF NOT EXISTS category (
id INTEGER PRIMARY KEY,
categoryUser varchar, 
name varchar(100),
FOREIGN KEY (categoryUser) REFERENCES user(username)
);
 
 CREATE TABLE IF NOT EXISTS user (
 id INTEGER PRIMARY KEY, 
 username VARCHAR(100),
 password VARCHAR(100),
 UNIQUE(username, password)
 );
 
 CREATE TABLE IF NOT EXISTS expense (
 id INTEGER PRIMARY KEY, 
 user_username VARCHAR(100),
 category_name varchar,
 amount float,
 time DATE,
 FOREIGN KEY (user_username) REFERENCES user(username),
 FOREIGN KEY(category_name) REFERENCES category(name)
 );
 
 CREATE TABLE IF NOT EXISTS income (
 id INTEGER PRIMARY KEY, 
 user_username VARCHAR(100),
 amount float,
 time DATE,
 FOREIGN KEY (user_username) REFERENCES user(username),
 );
 
 CREATE TABLE IF NOT EXISTS balance (
 id INTEGER PRIMARY KEY, 
 user_username VARCHAR(100),
 amount float,
 time DATE,
 FOREIGN KEY (user_username) REFERENCES user(username),
 );
 
 
### Main Functionalities

#### Login

#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetApp Login Sequence Diagram.png">

#### New User Sign Up


#### Othe Functionalities





