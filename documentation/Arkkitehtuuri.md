# Architecture of the Program

## Structure

The program uses a three-tier architecture, and the package structure of the code is following:

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/Structure.png" width = "300" height = "500">

## Graphical User Interface

The GUI consistis of three scenese:
- login
- sign up 
- MyBudgetApp scene

## Application Logic

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/Sovelluslogiikka.png" width = "650" height = "270">>

MyBudgetService is responsible for the interaction between the Graphical User Interface and the database.

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

In the login window, the user inputs his/her username and password into the appropriate text fields and clicks the "login" button. If the user does not have an existing username and password, the user clicks on the "sign up" button

#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetApp Login Sequence Diagram.png">

#### New User Sign Up

In the sign up window, the user creates a new username and password and click on the confirm button.

#### Other Functionalities
##### Recording Financial Transactions

After logging in, the user can input data regarding his/her income or expense as well create new expense cateogories. The data regarding the amount of money is entered into the corresponding textfields and the datepicker object is used to set the time of transaction.

When a new category is created, the gui invokes the createCateogory method in MyBudgetService class, with the username and the category as its parameters.

When a new expense is recorded, the gui invokes the createExpense method of the MyBudgetService class, with the username, category name, amount, and date as its parameters.

When a new income is recorded, the gui invokes the createIncome method of the MyBudgetService class, with the username, amount, and date as its parameters.





