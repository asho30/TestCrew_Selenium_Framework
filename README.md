# TestCrew_Test

Environment Requirements:
• Project: Java/Maven project with TestNG integration.
• Frameworks: Selenium WebDriver with some customization and integration with Cucumber BDD framework.

Technical requirements:
• The project implements modular design by using the Page Object Model design pattern.
• Acceptable design and distribution of the test cases/classes.
• Externalizing test data “config.properties”.
• Creating an automated test execution report in test-output/index.html in case of TestNG.
• Creating a json, xml, & Html reports in the target folder in case of Cucumber.
• Screenshot has been attached to the report.
• Response body has been attached to the report (in case of API).
• ReadMe file describing the project and how to use has been attached.
• The project as a GitHub repo on this link:
https://github.com/asho30/TestCrew_Selenium_Framework

# import project to eclipse and you can run it from testng.xml as TestNG test or from TestRunner.java as Cucumber test.
# Or by Maven: copy the project folder to D:/ driver then run the test from CMD by the following lines:

cd D:\TestCrew_Selenium_Framework

D:

mvn clean

mvn compile

mvn test
# TestCrew_Test
