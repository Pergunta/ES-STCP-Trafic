Feature:Check common routes between buses 

Scenario:Common routes available 
    Given Ricardo missed his bus and needs to go to work
    When he visits Future Traffic 
    Then he finds another bus with a common route.