Feature: Scenario of pet shop api testing

  Scenario: Adding new pet

    Given Pet has category with following parameters
      | id | categoryName |
      | 1  | dog          |

    Given Pet has tags with following parameters
      | id | tagName     |
      | 1  | white       |
      | 2  | loudBarking |

    Given User has "addNewPetRQ" request with following parameters
      | id | category | name | photoUrls  | tags | status    |
      | 12 | 1        | Rex  | url1, url2 | 1, 2  | available |

    When User sends "POST" "addNewPetRQ" request

    # IDK why all post responses has code 200 instead of 201
    Then "addNewPetRS" code is "200"
