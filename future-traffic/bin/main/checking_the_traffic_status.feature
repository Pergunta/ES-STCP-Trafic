
Feature: Check the traffic status 


Scenario: Elisa is a STCP manager
    Given Elisa is a manager of STCP
    When she opens Future Traffic real time map
    Then she can check status on traffic.
