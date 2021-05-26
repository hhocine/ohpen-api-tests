package com.ohpen.bdd.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", plugin = { "pretty", "html:target/cucumber",
	"json:target/cucumber/cucumber.json" }, glue = { "com.ohpen.bdd.glue" }, tags = { "not @ignore" })
public class CukeRunner {

}
