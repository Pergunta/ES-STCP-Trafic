# Feature:Check common routes between buses 

# Scenario Outline: Common routes available 
#     Given Ricardo is at the <street>
#     When he visits Future Traffic 
#     Then he finds <int> bus with a common route.

#     Examples:
#     | street                           | int   |
#     | Rua Interior in Porto            |       |
#     | anything else!                   | can't |