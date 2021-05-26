Feature: Gist CRUD
  Validating the gist feature.
  Tests variables are the presence of a description.

  Scenario: Creating a gist then delete - Nominal case
    Given I define a gist file named "helloworld.java" with the following file content
      """
      System.out.println("Hello World");
      """
    When I create a gist with the defined file and the description "hello world"
    And I get the list of gists
    Then The list of gists should contain the created gist
    When I delete the created gist
    And I get the list of gists
    Then The list of gists should not contain the created gist

  Scenario: Creating a gist then delete - No description
    Given I define a gist file named "helloworld.java" with the following file content
      """
      System.out.println("Hello World");
      """
    When I create a gist with the defined file and no description
    Then I should get an invalid request exception
