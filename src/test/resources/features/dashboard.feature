@test @db
Feature: Dashboard Page

  Scenario: Dashboard data test_01 and test 01
    Given the user logged in as "librarian"
    When user gets all information from modules
    Then the informations should be same with database