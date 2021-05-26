Feature: Organization Repositories
  Validating the listing of repositories within an organization. Tests variables are the organization name.

  Scenario: Repositories within an organization - Nominal case
    When I get the repositories within the organization "adobe"
    Then The list of repositories should contain "brackets-app,brackets,adobe.github.com"
    
	Scenario: Repositories within an organization - Error case - Invalid organization
    When I get the repositories within the organization "toto"
    Then The list of repositories should be empty

