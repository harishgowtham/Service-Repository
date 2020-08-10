package com.qdexpro.bean.response;

import java.util.List;

import com.qdexpro.controller.TeardownResponse;

public class ScenarioDetailsResponse {
	
	private ScenarioResponse scenarioResponse;
	private SetupResponse setupResponse;
	private List<StepDetails> setupStepDetails;
	private TeardownResponse teardownResponse;
	private List<StepDetails> tearDownStepDetails;
	
	private List<TestCaseDetails> testCaseDetails;

	public ScenarioResponse getScenarioResponse() {
		return scenarioResponse;
	}

	public void setScenarioResponse(ScenarioResponse scenarioResponse) {
		this.scenarioResponse = scenarioResponse;
	}

	public SetupResponse getSetupResponse() {
		return setupResponse;
	}

	public void setSetupResponse(SetupResponse setupResponse) {
		this.setupResponse = setupResponse;
	}

	public List<StepDetails> getSetupStepDetails() {
		return setupStepDetails;
	}

	public void setSetupStepDetails(List<StepDetails> setupStepDetails) {
		this.setupStepDetails = setupStepDetails;
	}

	public TeardownResponse getTeardownResponse() {
		return teardownResponse;
	}

	public void setTeardownResponse(TeardownResponse teardownResponse) {
		this.teardownResponse = teardownResponse;
	}

	public List<StepDetails> getTearDownStepDetails() {
		return tearDownStepDetails;
	}

	public void setTearDownStepDetails(List<StepDetails> tearDownStepDetails) {
		this.tearDownStepDetails = tearDownStepDetails;
	}

	public List<TestCaseDetails> getTestCaseDetails() {
		return testCaseDetails;
	}

	public void setTestCaseDetails(List<TestCaseDetails> testCaseDetails) {
		this.testCaseDetails = testCaseDetails;
	}
	
	

}
