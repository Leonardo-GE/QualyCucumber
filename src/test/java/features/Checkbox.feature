Feature: Checkbox


  // TEST Scenario
  Scenario: Checkbox Test

    Given The User goes to the "Checkboxes" page from the Main Page
    When  User verifies checkboxes exist
    When User checks first checkbox
    When User unchecks second checkbox
    Then The 1st checkbox should be checked and 2nd should be unchecked
