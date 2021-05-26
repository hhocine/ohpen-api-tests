package com.ohpen.bdd.glue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ohpen.bdd.data.ScenarioRuntimeData;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RepositorySteps {

    private static Logger logger = LoggerFactory.getLogger(RepositorySteps.class);
    private ScenarioRuntimeData scenarioRuntimeData;
    List<Repository> repositories;


    public RepositorySteps(ScenarioRuntimeData scenarioRuntimeData) {
	this.scenarioRuntimeData = scenarioRuntimeData;
    }

    @When("I get the repositories within the organization {string}")
    public void i_get_the_repositories_within_the_organization(String organization) throws IOException {
	repositories = new ArrayList<>();
	RepositoryService repoService = new RepositoryService(scenarioRuntimeData.getClient());

	// here we catch the requestException as it might be expected in the scenario to
	// get it (ex: invalid organization)
	try {
	    repositories = repoService.getOrgRepositories(organization);
	} catch (RequestException e) {
	    logger.warn(String.format(
		    "Could not get the repositories of organization %s. Might be an expected behaviour", organization));
	}
    }

    @Then("The list of repositories should contain {list}")
    public void the_list_of_repositories_should_contain(List<String> expectedNames) {
	List<String> actualNames = new ArrayList<>();
	for (Repository repository : repositories) {
	    actualNames.add(repository.getName());
	}
	assertTrue("List of repositories names should contain the expected names",
		actualNames.containsAll(expectedNames));
    }

    @Then("The list of repositories should be empty")
    public void the_list_of_repositories_should_be_empty() {
	assertThat("The list of repositories should be empty", repositories, empty());
    }

}
