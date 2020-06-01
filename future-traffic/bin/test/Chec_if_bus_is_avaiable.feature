Feature: Check if the bus is available

Scenario:  Bus  is available 

Given Julio is at Rua Central de Francos 
And  he thinks the next bus is the bus 2518 at "Rua Interior in Porto"  
When he goes to "Rua Interior in Porto"   
Then he confirms the next Bus is 2518 in "Rua Interior in Porto"

