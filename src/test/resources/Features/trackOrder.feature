Feature: List of Track a Order Scenarios

  @track
  Scenario: Verify Order number and Phone number present on track order page
    Given user navigates to the urbanLadder application
    And I close the Sign or Sign up pop-up
    When user clicks on Track Order option
    Then user navigate to Track Order page
    And I validate "Track Order" page
    And Verify Phone Number present on Track Order Page
    And Order Number present on Track Order Page

  @track @signin
  Scenario: Verify SignIn in with Social Media is enable for Tracking
    Given user navigates to the urbanLadder application
    And I close the Sign or Sign up pop-up
    And user clicks on Track Order option
    And user navigate to Track Order page
    And I validate "Track Order" page
    When user clicks on Login to Track your orders button
    Then user sees social media option for login

  @track @signin
  Scenario: Verify Sign up option available while login for tack order
    Given user navigates to the urbanLadder application
    And I close the Sign or Sign up pop-up
    And user clicks on Track Order option
    And user navigate to Track Order page
    And I validate "Track Order" page
    When user clicks on Login to Track your orders button
    Then Verify Sign Off link available