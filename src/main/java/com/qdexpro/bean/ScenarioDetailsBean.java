package com.qdexpro.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScenarioDetailsBean {
    @JsonProperty(value ="scenarioName",required = true)
	private String scenarioName;
    @JsonProperty(value ="setup",required = false)
	private SetupBean setup;
    @JsonProperty(value ="testCase",required = false)
    private List<TestCaseBean> testCase;
    @JsonProperty(value ="teardown",required = false)
    private TearDown teardown;
	
	public SetupBean getSetup() {
		return setup;
	}
	public void setSetup(SetupBean setup) {
		this.setup = setup;
	}
	public List<TestCaseBean> getTestCase() {
		return testCase;
	}
	public void setTestCase(List<TestCaseBean> testCase) {
		this.testCase = testCase;
	}
	public TearDown getTeardown() {
		return teardown;
	}
	public void setTeardown(TearDown teardown) {
		this.teardown = teardown;
	}
	public String getScenarioName() {
		return scenarioName;
	}
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}
	
	

	
}
