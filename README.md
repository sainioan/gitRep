# Projekti (MyBudgetApp)

## <h2>Dokumentation
  
[Vaatimusmäärittely](../master/documentation/Vaatimusmaarittely.md)

[Tuntikirjanpito](../master/documentation/Tuntikirjanpito.md)

[Arkkitehtuurikuvaus](../master/documentation/Arkkitehtuuri.md)

[Testausdokumentti](../master/documentation/Testausdokumentti.md)

[Käyttöohje](../master/documentation/Kayttoohje.md)

## Releases

[Viikko5 Release](https://github.com/sainioan/gitRep/releases/tag/Viikko5)

[Viikko6 Release](https://github.com/sainioan/gitRep/releases/tag/Viikko6)

[Viikko7 Release/Final Release](https://github.com/sainioan/gitRep/releases/tag/viikko7)




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

