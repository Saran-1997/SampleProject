@Login
Feature: Login Module API Automation

  Scenario: Get user logtoken from login endpoint
    Given User should add header
    And User should add basic authentication for login
    When User should "POST" request for login endpoint
    Then User verify the status code is 200
    And User verify the login response body firstName present as "saran" and get the logtoken
