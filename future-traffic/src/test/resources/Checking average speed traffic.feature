Feature: Check average speed traffic


Scenario Outline: Jose confirms that X buses exceeding the speed limit
Given Jose has the information that there are at least <Busnumbers> Buses  exceeding the speed limit of <speed limit>
When he checks all the buses average speed 
Then he has the confirmation there are at least <Busnumbers> buses above speed <speed limit>.


Examples:
    | speed limit            | Busnumbers   |
    |  90                    |  0           |