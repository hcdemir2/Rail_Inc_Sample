Feature: As a public user, I should be able to search Products & Services

  Background: Public user is on the Products & Services page
    Given public user is on the home page
    And navigates to the Products & Services page

  @products
  Scenario: Products & Services page displays at least 45 results by default
    Then user should see at least 45 results

  @products
  Scenario Outline: : User can search products & services by search term only
    When user enters valid "<search_term>" in search box and clicks apply button
    Then title of each result contains "<search_term>"
    Examples: search term test data
      | search_term |
      | car         |
      | rail        |
      | service     |

  @products
  Scenario Outline: User can filter products & services by category only
    When user searches by category "<category>"
    Then user should see less than 45 results
    Examples: category examples
      | category                |
      | AAR Information/Embargo |
      | Component Services      |

  @products
  Scenario Outline: : User can search products & services by search term and category
    When user searches by category "<category>"
    And user sees less than 45 results
    And user enters valid "<search_term>" in search box and clicks apply button
    Then title of each result contains "<search_term>"
    And each result belongs to the category chosen
    Examples: search term test data
      | category                | search_term |
      | AAR Information/Embargo | aar         |
      | Car Owner Services      | damaged     |
      | Data Exchanges          | exchange    |
