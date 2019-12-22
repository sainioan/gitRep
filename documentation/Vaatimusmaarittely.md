# Software Requirements Specification

## Purpose of the Application

The application is a budget application intended for managing personal finances. 
Users can track their incomes and expenses. To use the application, the user has to login into the application.

## Users

Individuals, who have a user account, can sign in with their username and password, and use the MyBudgetApp to manage their personal finances.

## User interface draft

The user interface consists of five views with this layout:

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/UserInterfaceDraft.jpg" width="500" height="400">

## Functions of the App
### Before Login
* User can sign up for the app by create a unique username and password. 
* Username must be unique and at least 5 characters long. 
* User can cancel the sign up by pressing the "back" button. 
* User can login with a username and password.

### After Login
* User can logout. 
* The user should be able to add an income entry with amount and date.
* The user should be able to add an enxpense entry with category, amount, and date. 
* The user should be able to add his/her expenses against different categories such as groceries, gas, medical, etc. 
* The user should be able to add new categories of expenses.
* The user should be able to remove his/her account. 
* The user should be able to view his/her balance.
* The user should be able to view his/her budget history.
* The user should be able to view his/her spending in different categories using a piechart.


## User interface and functions

The software has a graphical user interface that consists of 5 different views.

The views are:

1. **Login view**. Here a registered user can login and a new user can ask to create an account.
2. **Sign up view**. User can either create an account or go back to login view without creating an account.
3. **Main view**. User can add new categories as well as input income and expense entries. User can also click on Tableview button, which will open the table view scene. In addition, user can delete their user account. Finally, user can exit the session by clicking the logout button.
4. **Table view**. A scene that consists of three tables: balance, income, and expense. 
5. **Piechart view**. A scene that consists of a spending by category piechart and a back button.

## Development Ideas

* The user should be able to add income to different categories (e.ge. salary).
* The user should be able to use an undo button that reverses the created entry.
* The user should be able to use an undo button that reverses the created entry.
* The tableview methods should be in the dao and service classes instead of the gui class.
* Currently, the combobox duplicates some values when creating a new category. However, the problem disappears when
the application is relaunched. This bug should be fixed.
