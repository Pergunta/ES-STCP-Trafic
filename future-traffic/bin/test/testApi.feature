Feature: Testing API

   Scenario: GET Buses information 
   Given I set GET buses service endpoint 
   When I send a request status 
   Then I recieve a "valid" response 