package com.qdexpro.bean.response;

public class ScenarioResponse {

	private String scenarioId;
	private String scenarioName;
	private String setup;
	private String tearDown;
	private String testCase;
	public String getScenarioId() {
		return scenarioId;
	}
	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}
	public String getScenarioName() {
		return scenarioName;
	}
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}
	public String getSetup() {
		return setup;
	}
	public void setSetup(String setup) {
		this.setup = setup;
	}
	public String getTearDown() {
		return tearDown;
	}
	public void setTearDown(String tearDown) {
		this.tearDown = tearDown;
	}
	public String getTestCase() {
		return testCase;
	}
	public void setTestCase(String testCase) {
		this.testCase = testCase;
	}
	
	
}
