#Author: ahmed.asho@yahoo.com
#Test data is taken from the following file "src/test/resources/TestDataFile.xlsx"
#The below data-driven Examples for controlling the countries that we want to run the test on it and its order in the data file.
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for data table
@subscription_plans_details_feature
Feature: Validate Subscription Plans Details

  @subscription_plans_details_scenario_outline
  Scenario Outline: Validate Subscription Plans Details
    Given the user is on subscribe page
    And has the subscription plans details <countryNumber>
    When changes the country
    Then validate the Price and Currency for each plan

    Examples: 
      | country | countryNumber |
      | KSA     |             1 |
      | Kuwait  |             2 |
      | Bahrain |             3 |