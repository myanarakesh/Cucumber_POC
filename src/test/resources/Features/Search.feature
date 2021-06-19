Feature: List of Search Scenarios

  @search
  Scenario Outline: Search a <searchAProduct> product in search bar and verify the result
    Given user navigates to the urbanLadder application
    And I validate "Home" page
    And I close the Sign or Sign up pop-up
    When i enter <searchAProduct> in search bar
    And i click on Search Icon in search bar
    And I see search result page of <searchAProduct>
    Then I verify <searchAProduct> search result

    Examples:
      | searchAProduct |
      | Dining Table   |
      | Wardrobe       |

    @search
    Scenario Outline: Validate <searchAProduct> Advance search option of products
      Given user navigates to the urbanLadder application
      And I close the Sign or Sign up pop-up
      When i enter <searchAProduct> in search bar
      Then I verify <searchAProduct> options under search bar

      Examples:
        | searchAProduct |
        | Dining Table   |
        | Wardrobe       |
