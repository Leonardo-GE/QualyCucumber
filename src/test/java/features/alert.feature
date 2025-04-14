Feature: Alerts



  Scenario: Alert Handling

    Given The User goes to the "JavaScript Alerts" page from the Main Page
    When The User generated the JS Alert
    And The User Accepts the Alert
    Then The Success message is correctly displayed