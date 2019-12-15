# Requirements Specification

## Purpose of the Application

The application is a budget application intended for managing personal finances. 
Users can track their incomes and expenses. To use the application, the user has to login into the application.

## Users

Individuals, who have a user account, can sign in with their username and password, and use the MyBudgetApp to manage their personal finances.

## Functions of the App


### Before Login
* User can sign up for the app by create a unique username and password. -- done
* Username must be unique and at least 5 characters long. -- done
* User can cancel the sign up by pressing the "back" button. -- done
* User can login with a username and password. -- done

### After Login
* User can logout. -- done
* The user should be able to add income amount and date.-- done
* The user should be able to add enxpense amount and date. -- done
* The user should be able to add his/her expenses against different categories such as groceries, gas, medical, etc. -- done
* The user should be able to add new categories of expenses. -- done
* The user should be able remove old categories of expenses.
* The user should be able to remove his/her account. -- done
* The user should be able to view his/her balance. -- done (works partially: changes saved when the next time the program is run )
* The user should be able to view financial statistics (if time).
## User interface and functions

The software has a graphical user interface that consists of 4 different views.

[The ](https://github.com/)

The views are:

1. **Login view**. Here a registered user can login and a new user can ask to create an account.
2. **Sign upview**. User can either create an account or go back to login view without creating an account.
3. **Mainview**. User can add new categories as well as input income and expense entries. User can also click on Tableview button, which will open the table view scene. In addition, user can delete their user account. Finally, user can exit the session by clicking the logout button.
4. **Tableview**. A scene that consists of three tables: balance, income, and expense. 

## Development Ideas

* The user should be able to add income to different categories (salary).
* The user should be able to view graphical representation of user data.
