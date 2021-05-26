package com.ohpen.bdd.glue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.service.GistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ohpen.bdd.data.ScenarioRuntimeData;
import com.ohpen.bdd.data.TestGlobalConfiguration;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GistSteps {

    private static Logger logger = LoggerFactory.getLogger(GistSteps.class);
    private ScenarioRuntimeData scenarioRuntimeData;
    private List<Gist> gists;
    private Map<String, GistFile> gistFiles = new HashMap<>();
    private Gist createdGist;
    boolean invalidRequestExceptionRaised;

    public GistSteps(ScenarioRuntimeData scenarioRuntimeData) {
	this.scenarioRuntimeData = scenarioRuntimeData;
    }

    @Given("I define a gist file named {string} with the following file content")
    public void i_define_a_gist_file_named_with_the_following_file_content(String fileName, String fileContent) {
	GistFile file = new GistFile();
	file.setContent(fileContent);
	gistFiles.put(fileName, file);
    }

    @When("I create a gist with the defined file and the description {string}")
    public void i_create_a_gist_with_the_defined_file_and_the_description(String description) throws IOException {
	Gist gist = new Gist();
	gist.setFiles(gistFiles);
	GistService service = new GistService(scenarioRuntimeData.getClient());

	gist.setDescription(description);
	createdGist = service.createGist(gist);
    }

    @When("I create a gist with the defined file and no description")
    public void i_create_a_gist_with_the_defined_file_and_the_description() throws IOException {
	Gist gist = new Gist();
	gist.setFiles(gistFiles);
	GistService service = new GistService(scenarioRuntimeData.getClient());

	invalidRequestExceptionRaised = false;

	try {
	    createdGist = service.createGist(gist);
	} catch (RequestException e) {
	    logger.debug("Exception caught while creating a gist without description as expected");
	    invalidRequestExceptionRaised = true;
	}
    }

    @Then("I should get an invalid request exception")
    public void i_should_get_an_invalid_request_exception() {
	assertTrue("Invalid request exception was not raised in the current scenario as expected",
		invalidRequestExceptionRaised);
    }

    @When("I get the list of gists")
    public void i_get_the_list_of_gists() throws IOException {
	GistService service = new GistService(scenarioRuntimeData.getClient());
	gists = service.getGists(TestGlobalConfiguration.getClientUsername());
    }

    @Then("The list of gists should contain the created gist")
    public void the_list_of_gists_should_contain_a_gist_with_the_description() {
	assertTrue(gists.stream().anyMatch(gist -> createdGist.getId().equals(gist.getId())));
    }

    @Given("I delete the created gist")
    public void i_delete_the_created_gist() throws IOException {
	GistService service = new GistService(scenarioRuntimeData.getClient());
	service.deleteGist(createdGist.getId());
    }

    @Then("The list of gists should not contain the created gist")
    public void the_list_of_gists_should_not_contain_the_created_gist_anymore() {
	assertFalse(gists.stream().anyMatch(gist -> createdGist.getId().equals(gist.getId())));
    }

}
