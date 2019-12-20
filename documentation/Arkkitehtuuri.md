# Architecture of the Program

## Structure

The program uses a three-tier architecture, and the package structure of the code is following:

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/Structure.png" width = "300" height = "700">

The package mybudget.ui contains the JavaFX-based graphical user interface(GUI), the package mybudgetapp.domain contains the the classes responsible for the application logic, and the package mybudgetapp.dao contains the classes responsible for storing data permanently into the database.

## Graphical User Interface

The GUI consistis of five scenes:
- User login scene
- Sign up scene
- MyBudgetApp scene
- Table view (budget history)
- Pie chart scene (spending by category)


User interface scenes are Scene objects created in the classes of the *ui* package. The user interface has been created programmatically in the mybudget.ui.MyBudgetAppUI.

The user interface has been separated from the application logic for the most part. The user interface calls methods that implement application logic through MyBudgetAppService, with only a little application logic in the user interface class.

## Application Logic
The application's logical data model consists of the following classes:
- [User](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/main/java/mybudgetapp/domain/User.java) 
- [Balance](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/main/java/mybudgetapp/domain/Balance.java)
- [Income](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/main/java/mybudgetapp/domain/Income.java)
- [Expense](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/main/java/mybudgetapp/domain/Expense.java)
- [Category](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/main/java/mybudgetapp/domain/Category.java)

The user has one balance, which is the amount of money the user owns. The balance is changed every time the to inputs an income or expense. The expenses are grouped by category. The user can also add new expense categories to the existing ones.

MyBudgetService is responsible for the interaction between the Graphical User Interface and the database. The class
contains, for example, the following methods:
- boolean createUser(String username, String password)
- boolean createCategory(String username, String description)
- boolean createIncome(String username, double amount, LocalDate date)
- boolean updateBalanceNewIncome(String username, double income, LocalDate date)
- boolean updateBalanceNewExpense(String username, double expense, LocalDate date)
- String updateBalanceLabel()

### MyBudgetApp Class Diagram 
<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppClassDiagram.png" width= "600" height= "500">


### MyBudgetApp Package Diagram
<img src="https://github.com/sainioan/gitRep/blob/master/pictures/class diagram.png" width= "300" height= "400">

## Data storage

The application follows the  [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) design pattern and stores data in an sql ddtabase.

## Database

Data about the users and their financial transactions is saved into mybudgetapp.db, which is an SQLite database. Mybudgetapp.db consists of 5 tables: category, user, expense, income, and balance.
The SQL statements for creating the database tables.

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
 time varchar,
 FOREIGN KEY (user_username) REFERENCES user(username),
 FOREIGN KEY(category_name) REFERENCES category(name)
 );
 
 CREATE TABLE IF NOT EXISTS income (
 id INTEGER PRIMARY KEY, 
 user_username VARCHAR(100),
 amount float,
 time varchar,
 FOREIGN KEY (user_username) REFERENCES user(username),
 );
 
 CREATE TABLE IF NOT EXISTS balance (
 id INTEGER PRIMARY KEY, 
 user_username VARCHAR(100),
 amount float,
 time varchar,
 FOREIGN KEY (user_username) REFERENCES user(username),
 );
 
 
### Main Functionalities

#### Login

In the login window, the user inputs his/her username and password into the appropriate text fields and clicks the "login" button. If the user does not have an existing username and password, the user clicks on the "sign up" button

#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetApp Login Sequence Diagram.png">

### New User Sign Up

In the sign up window, the user creates a new username and password and click on the confirm button.

#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppCreateUserSequenceDiagram.png">

### Create New Income

#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppCreateIncome.png">

### Create New Cateogry

#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppCreateCategory.png">

### Create New Expense

#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppCreateExpense.png">




#### Other Functionalities
##### Recording Financial Transactions

After logging in, the user can input data regarding his/her income or expense as well create new expense cateogories. The data regarding the amount of money is entered into the corresponding textfields and the datepicker object is used to set the time of transaction.

When a new category is created, the GUI invokes the createCateogory method in MyBudgetService class, with username and category as its parameters.

When a new expense is recorded, the GUI invokes the createExpense method of the MyBudgetService class, with username, category name, amount, and date as its parameters.

When a new income is recorded, the GUI invokes the createIncome method of the MyBudgetService class, with username, amount, and date as its parameters.

## Main weaknesses

### Graphical user interface
All of the GUI logic is in onemethod making it difficult to handle. A possible fix would be to create separate methods for building up each scene, but despite trying to create separate code blocks for building up the different scene, I was not able to get it to work.




