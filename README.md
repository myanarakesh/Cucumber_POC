## selenium-cucumber-java-maven

selenium-cucumber is a behavior driven development (BDD) approach to write automation test script to test Web. It enables you to write and execute automated acceptance/unit tests. It is cross-platform, open source and free. Automate your test cases with minimal coding.
**Why BDD?** BDD is a way for software teams to work that closes the gap between business people and technical people

This project follows page object model. So we can maintain separate file whcih has all the locators and methdos of respective pages. You can also run the parallel testing using Maven Surefire Plugin. One can also set the preferable browser for execution by changing `browser` property at `src/test/resources/Config/configuration.properties`

## Installing the project
Open the GitBash where you want to download the project. Then follow below steps
 ```
 git init
 git clone https://github.com/myanarakesh/Cucumber_POC.git
```
* Open your faviourate IDE (prefarable Eclipse or Intellij)
* Open the downloaded project as Maven
* Wait for all the dependencies to get downloaded.


## Running the Automation Script
There are several ways you can run your script
1. Running entire project
Goto the root directory and enter folloiwng command
```
mvn clean install
```
2. Running perticular scenarios <br />
Mention the tags in `UnitRunnerTest.java` file which is given to scenarios under Feature files. And run this file.

3. Running only perticular scenario (Applicable to only Intellij IDEA)
Right click on a scenario and Select the option as `Run:{Scenario Name}` option

## Cucumber Report
Once Cucumber project completed running all the scenarios. It will generate Cucumber Report under `target/cucumber-reports/advanced-reports/cucumber-html-reports` folder. <br />
While running any perticular scenario if it get failed then it also takes screenshot and store it under `target/screenshots` folder
