# Test Document

This program has been tested by automated unit- and intergation-tests with JUnit and manually performed systemlevel tests.

## Unit and Integration testing
### Application Logic

The core of the automated tests are testing softwarelogic package [mybudgetapp.domain](https://github.com/sainioan/gitRep/tree/master/MyBudgetApp/MyBudgetApp/src/main/java/mybudgetapp/domain). Classes testing this package are:

- [BalanceTest](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/test/java/mybudgetapp/domain/BalanceTest.java)

- [CategoryTest](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/test/java/mybudgetapp/domain/CategoryTest.java)

- [ExpenseTest](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/test/java/mybudgetapp/domain/ExpenseTest.java)

- [IncomeTest](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/test/java/mybudgetapp/domain/IncomeTest.java)

- [UserTest](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/test/java/mybudgetapp/domain/UserTest.java)
 
- [MyBudgetServiceTest](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/test/java/mybudgetapp/domain/MyBudgetServiceTest.java)

MyBudgetServiceTest has several integration tests, which test integration between software modules in the application. 
The integration tests use the DBaBudgetDao and DBUserDao classes to store data.

### DAO

Both DAO-classes have been tested by creating a test database, jdbc:sqlite:test.db. The DAO test classes test 
[mybudgetapp.dao](https://github.com/sainioan/gitRep/tree/master/MyBudgetApp/MyBudgetApp/src/main/java/mybudgetapp/dao)
The DAO test classes are:

- [DBBudgetDaoTest](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/test/java/mybudgetapp/dao/DBBudgetDaoTest.java)

- [DBUserDaoTest](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/test/java/mybudgetapp/dao/DBUserDaoTest.java)
##### Database

- [MyBudgetDatabaseTest](https://github.com/sainioan/gitRep/blob/master/MyBudgetApp/MyBudgetApp/src/test/java/mybudgetapp/dao/MyBudgetDatabaseTest.java)

### Test Code Coverage
Tests line coverage is 75 % of the code and the branch coverage is 71 % of the code.

<img src="https://github.com/sainioan/gitRep/blob/master/pictures/jacocoReport.png">



### Installation and Configuration

Program has been installed and tested on OIS and Windows operating systems.


### Functionalities

All the functionalities listed by [User guide](../master/documentation/Kayttoohje.md) and [Software Requirements Specification](../master/documentation/Vaatimusmaarittely.md) have been tested. Different kinds of user inputs have been tested by the program including empty or invalid values.
