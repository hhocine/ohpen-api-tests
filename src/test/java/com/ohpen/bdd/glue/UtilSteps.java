package com.ohpen.bdd.glue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ohpen.bdd.data.ScenarioRuntimeData;

import cucumber.api.java.en.Given;

public class UtilSteps {

    private ScenarioRuntimeData scenarioRuntimeData;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public UtilSteps(ScenarioRuntimeData scenarioRuntimeData) {
	this.scenarioRuntimeData = scenarioRuntimeData;
    }

    @Given("I wait {int} s")
    public void i_wait_s(Integer waitSec) throws InterruptedException {
	Thread.sleep(waitSec * 1000);
    }

}
