package com.ohpen.bdd.glue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ohpen.bdd.data.ScenarioRuntimeData;
import com.ohpen.bdd.data.TestGlobalConfiguration;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ScenarioHookSteps {

    private ScenarioRuntimeData scenarioRuntimeData;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public ScenarioHookSteps(ScenarioRuntimeData scenarioRuntimeData) {
	this.scenarioRuntimeData = scenarioRuntimeData;
    }

    @Before
    public void before(Scenario s) {
	logger.info(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime())
		+ " - Starting scenario [[[" + s.getName() + "]]]");
	scenarioRuntimeData.setScenarioName(s.getName());

	GitHubClient client = new GitHubClient();

	if (StringUtils.isNoneEmpty(TestGlobalConfiguration.getClientToken())) {
	    client.setOAuth2Token(TestGlobalConfiguration.getClientToken());
	} else if (StringUtils.isNoneEmpty(TestGlobalConfiguration.getClientUsername())) {
	    client.setCredentials(TestGlobalConfiguration.getClientUsername(),
		    TestGlobalConfiguration.getClientPassword());
	}
	scenarioRuntimeData.setClient(client);
    }

    @After
    public void after(Scenario s) {
	logger.info(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime())
		+ " - End of scenario [[[" + s.getName() + "]]] - " + s.getStatus());
    }
}
