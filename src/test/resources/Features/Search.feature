Feature: List of Search Scenarios

  @search
  Scenario Outline: Search a product in search bar and verify the result
    Given user navigates to the urbanLadder application
    And I close the Sign or Sign up pop-up
    When i enter <searchAProduct> in search bar
    And i click on Search Icon in search bar
    Then I verify <searchAProduct> search result

    Examples:
      | searchAProduct |
      | Dining Table   |
      | Wardrobe       |

    @search
    Scenario Outline: Validate Advance search option of products
      Given user navigates to the urbanLadder application
      And I close the Sign or Sign up pop-up
      When i enter <searchAProduct> in search bar
      Then I verify <searchAProduct> options under search bar

      Examples:
        | searchAProduct |
        | Dining Table   |
        | Wardrobe       |
