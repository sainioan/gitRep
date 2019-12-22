# User guide

Download the file [MyBudgetApp.jar](https://github.com/sainioan/gitRep/releases)

## Database file

MyBudgetApp application saves data about user income and expenses into a database file called mybudgetapp.db. After downloading and running the app, it creates a new database by the given name in the same directory where MyBudgetApp.jar is located. If the database file is deleted, the data will be lost permanently.

## Login

Once the app opens, the Login screen appears. If you have already signed up as a user, enter your username and password to access the application and click _login_.  

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppLogin.png"  width="400" height="250">

If you have not yet signed up for MyBudgetApp, enter a new username and password into the appropriate field and click _confirm_. 

The username has to be unique. In addition the username and password have to be at least five characters long.

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetApp_Sign_up.png" width="400" height="250">

## MyBudgetScene

After logging in, the main scene will open. Users input income and expenses on calendar dates to track their spending and balance.

The GUI main scene, allows the user to input income and expenses, add new expense categories as well as view their budget history and spending by categories. 

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppScene.png" width="700" height="500">

The required input for income consists of amount as double and the date, which the user can choose by clicking on the datepicker calendar. To confirm, click  _Save income_.

The required input for expense consists category, amount as double and the date, which the user can choose by clicking on the datepicker calendar. To confirm, click  _Save expense_.

To add a new category, enter category description and click  _Save new category_.

MyBudgetScene gives you the following functions:

- **Logout** button will log you out and open the login view.
- **Delete user account** button will delete the user's account and erase all data regarding the user from the database. 
- **Create expense category** button will add a new category to the list of expense category choices.
- **Save income** button will save the income entry inputted by the user. The required parameters for the income entry are
amount as double and date.
- **Save expense** button will save the expense entry inputted by the user. The required parameters for the expense entry are
expense category, amount as double, and date.
- **Your Balance Today** label shows the user's current balance.
- **Budget history table view** button will open a new scene with three tables that shows the user's budget history from the most recent transaction to the oldest. The three tables are: Your Balance History, Your Income History, and Your Expense History. Upto 30 entries will be shown.
- **Expenses by category piechart** button will open a new scene with a piechart that shows a piechart of the user's speding grouped by categories.

### MyBudgetHistory

To view your budgethistory, click _Budget history tableview_

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppBudgetHistory.png" width="300" height="600">

### Spending by categories

To view the spending pie chart, click _Expenses by category pie chart_ 

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/MyBudgetAppPieChartScene.png" width="390" height="315">
	
### Logout	
To logout of the application, click _sign out_
