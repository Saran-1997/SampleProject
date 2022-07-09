@ChangeProfilePic
Feature: ProfilePic Module API Automation

  Scenario: Verify change profilePic to the application through API
    Given User should add headers and bearer authourization for accessing change profile endpoint
    When user should perform multiPart to change the profilePic
    And User should "POST" request for change profilePic endpoint
    Then User verify the status code is 200
    And User verify the change profilePic response body message matches as "Profile updated Successfully"
