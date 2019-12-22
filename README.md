# Projekti (MyBudgetApp)


[Vaatimusmäärittely](../master/documentation/Vaatimusmaarittely.md)

[Tuntikirjanpito](../master/documentation/Tuntikirjanpito.md)

[Arkkitehtuurikuvaus](../master/documentation/Arkkitehtuuri.md)

[Viikko5 Release](https://github.com/sainioan/gitRep/releases/tag/Viikko5)

[Viikko6 Release](https://github.com/sainioan/gitRep/releases/tag/Viikko6)

[Viikko 7 Release/Final Release](https://github.com/sainioan/gitRep/releases/tag/Viikko7)

[Käyttöohje](../master/documentation/Kayttoohje.md)


###  Terminal Commands

#### Test
```
mvn test
```
#### Run .jar package
```
java -jar MyBudgetApp.jar
```

#### Compile & Run
```
mvn compile exec:java -Dexec.mainClass=mybudgetapp.Main
```

#### Generate Jacoco tests 
```
mvn test jacoco:report
```

#### Run Checkstyle Tests
```
mvn jxr:jxr checkstyle:checkstyle
```

#### Generate Javadoc

```
mvn javadoc:javadoc
```

