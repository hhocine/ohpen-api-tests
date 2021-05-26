package com.ohpen.bdd.data;

import org.eclipse.egit.github.core.client.GitHubClient;

/**
 *
 * Class used for Cucumber dependency injection
 *
 * @author hichem
 *
 */
public class ScenarioRuntimeData {

    private String scenarioName;
    private GitHubClient client = new GitHubClient();

    public ScenarioRuntimeData() {

	// gets set on scenario startup by @beforeScenario Hook
	scenarioName = "undefined";
    }

    public String getScenarioName() {
	return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
	this.scenarioName = scenarioName;
    }

    public GitHubClient getClient() {
	return client;
    }

    public void setClient(GitHubClient client) {
	this.client = client;
    }

}
