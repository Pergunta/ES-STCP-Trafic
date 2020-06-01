
Feature: Check parked Buses 

 Scenario Outline: Elisa  wants to confirm how many buses are parked in the Bus Station X
     Given Elisa has the information that  "<bus station>" has at least <answer> parked buses
     When she checks how many buses are parked at the "<bus station>"
     Then she can confirm there are at least <answer> buses parked in  "<bus station>".

Examples:
    | bus station                        | answer  |
    | Rua Interior in Porto              |  10     |
    | Rua Sarah Afonso                   |  0      |



