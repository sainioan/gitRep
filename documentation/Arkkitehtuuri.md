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

### MyBudgetApp Class Diagram 
<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppClassDiagram.png" width= "600" height= "500">

MyBudgetService is responsible for the interaction between the Graphical User Interface and the database. The class
contains, for example, the following methods:
- boolean createUser(String username, String password)
- boolean createCategory(String username, String description)
- boolean createIncome(String username, double amount, LocalDate date)
- boolean updateBalanceNewIncome(String username, double income, LocalDate date)
- boolean updateBalanceNewExpense(String username, double expense, LocalDate date)
- String updateBalanceLabel

MyBudgetService can access users and the users' Balance information through the classes that implement the BudgetDao and UserDao interface in the mybudgetapp.dao, the package responsible for storing data. The Dao injects the class implementaion into the service when the constructor (with one or more arguments) is called.

### MyBudgetApp Package Diagram
The diagram shows the relationship between MyBudgetService and the other parts of the program
<img src="https://github.com/sainioan/gitRep/blob/master/pictures/class diagram.png" width= "300" height= "400">

## Data storage

The classes DBUserDao and DBBudgetDao in the mybudgetapp.dao package are responsible for storing data into the mybudgetapp.db, an sql database. The classes the [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) design pattern.

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
 
 The application stores data in the following format
 
user
<pre>
1|testUser|TU123
2|elephant|12345
</pre>

category
<pre>
1|testUser|food
1|testUser|food
2|testUser|travel
3|testUser|entertainment
4|elephant|groceries
</pre>

income 
<pre> 
1|testUser|1000.0|2019/12/02
2|elephant|1000000000.0|2019/11/06
</pre>

expense
<pre>
1|testUser|food|25.0|2019/12/13
2|elephant|entertainment|50.0|2019/12/03
</pre>

balance
<pre>
22|elephant|999986176.0|2019/12/19
23|testUser|1011450.0|2019/12/19
</pre>

## Main Functionalities

### Login

In the login window, the user inputs his/her username and password into the appropriate text fields and clicks the "login" button. If the user does not have an existing username and password, the user clicks on the "sign up" button

#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetApp Login Sequence Diagram.png">

### New User Sign Up

In the sign up window, the user creates a new username and password and click on the confirm button.

#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppCreateUserSequenceDiagram.png">

### Create New Income

After logging in, the user can input data regarding his/her income. The user enters the amount of money into the corresponding textfield, and selects the the time of transaction using the datepicker object. The initial balance is 0.0 by default. The current balance changes as a result of inputting an income. 
  

When a new income is recorded, the GUI invokes the createIncome method of the MyBudgetService class, with username, amount, and date as its parameters.
#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppCreateIncome.png">

### Create New Cateogry

After logging in, the user can create new expense cateogories. 

When a new category is created, the GUI invokes the createCateogory method in MyBudgetService class, with username and category as its parameters. 

#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppCreateCategory.png">

### Create New Expense

After logging in, the user can input data regarding his/her expense. The user selects an item from the ComboBox's list of expense categories, enters the amount of money into the corresponding textfield, and selects the the time of transaction using the datepicker object. The current balance changes as a result of inputting an expense. 

When a new expense is recorded, the GUI invokes the createExpense method of the MyBudgetService class, with username, category name, amount, and date as its parameters.

#### Sequence Diagram

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppCreateExpense.png">

### Viewing Financial Transactions

#### Sequence Diagram

View Budget History 

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppViewBudgetHistorySequenceDiagram.png">

#### Sequence Diagram

View Spending by Category

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppViewPieChartSequenceDiagram.png">

### Other Functionalities

The user can delete his/her account permanently.

### Main weaknesses

#### Graphical user interface

All of the GUI logic is in one method making it difficult to handle. A possible fix would be to create separate methods for building up each scene, but despite trying to create separate code blocks for building up the different scenes, I was not able to get it to work. 

In addition, the buildup (buildUpBalance, buildUpIncome, buildUpExpense) methods for creating the tableviews shown in the tableScene are in the GUI class. I tried placing these methods in the DBBudgetDao class, but the methods did not work correctly.




