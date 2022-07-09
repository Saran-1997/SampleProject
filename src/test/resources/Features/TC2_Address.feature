@Address
Feature: Address Module API Automation

  Scenario Outline: Verify add new address and get the address_id to the application through API
    Given User should add headers and bearer authourization for accessing address endpoint
    When User should add request body for Add new address "<first_name>", "<last_name>", "<mobile>", "<apartment>", "<state>", "<city>", "<country>", "<zipcode>", "<address>" and "<address_type>"
    And User should "POST" request for add new address
    Then User verify the status code is 200
    And User verify the create address response message matches "Address added successfully" and get the address_id

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address          | address_type |
      | saran      | kumaran   | 9962005457 | apartment |    33 | 3378 |     101 |  202020 | 33/24ECR Chennai | Apartment    |

  Scenario Outline: Verify update address to the application through API
    Given User should add headers and bearer authourization for accessing address endpoint
    When User should add request body for Update new address "<first_name>", "<last_name>", "<mobile>", "<apartment>", "<state>", "<city>", "<country>", "<zipcode>", "<address>" and "<address_type>"
    And User should "PUT" request for update existing address
    Then User verify the status code is 200
    And User verify the update address response message matches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address          | address_type |
      | saran      | kumaran   | 9962005457 | apartment |    33 | 3378 |     101 |  202020 | 33/24ECR Chennai | Apartment    |

  Scenario: Verify get address to the application through API
    Given User should add headers and bearer authourization for accessing address endpoint
    When User should "GET" request for getting all address
    Then User verify the status code is 200
    And User verify the get address response message matches "OK"

  Scenario: Verify delete address to the application through API
    Given User should add headers and bearer authourization for accessing address endpoint
    When User should add request body for Delete address using "<address_id>"
    And User should "DELETE" request for deleting the generated address
    Then User verify the status code is 200
    And User verify the delete address response message matches "Address deleted successfully"
