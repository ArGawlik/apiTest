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
      | 12 | 1        | Rex  | url1, url2 | 1, 2 | available |

    When User sends "POST" "addNewPetRQ" request

    # IDK why all post responses has code 200 instead of 201
    Then "addNewPetRS" code is "200"
    And Pet name in "addNewPetRS" response is "Rex"

  Scenario: Getting pet by id

    Given User has "getPetByIdRQ" request with id "12"

    When User sends "GET" "getPetByIdRQ" request

    Then "getPetByIdRS" code is "200"
    And Pet name in "getPetByIdRS" response is "Rex"

  Scenario: Adding new user

    Given User has "addNewUserRQ" request with following parameters
      | id | username    | firstName | lastName | email             | password             | phone       | userStatus |
      | 32 | doglover123 | John      | Rambo    | stalone@gmail.com | some_secret_password | +1987654321 | 4          |

    When User sends "POST" "addNewUserRQ" request

    Then "addNewUserRS" code is "200"
    And Message for "addNewUserRS" response is "32"

  Scenario: Getting user by username

    Given User has "getUserByUsernameRQ" request with username "doglover123"

    When User sends "GET" "getUserByUsernameRQ" request

    Then "getUserByUsernameRS" code is "200"
    And User last name for "getUserByUsernameRS" is "Rambo"

  Scenario: Deleting user by username

    Given User has "deleteUserByUsernameRQ" delete request with username "doglover123"

    When User sends "DELETE" "deleteUserByUsernameRQ" request

    Then "deleteUserByUsernameRS" code is "200"
    And Message for "deleteUserByUsernameRS" response is "doglover123"

  Scenario: Getting deleted user - expected 404

    Given User has "getUserByUsernameRQ" request with username "doglover123"

    When User sends "GET" "getUserByUsernameRQ" request

    Then "getUserByUsernameRS" code is "404"

  Scenario: Getting all pets by status

    Given User has "getPetsByStatusRQ" request with status "available" parameter

    When User sends "GET" "getPetsByStatusRQ" request

    Then "getPetsByStatusRS" code is "200"
    And All pets from "getPetsByStatusRS" have status "available"