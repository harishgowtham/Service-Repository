package com.qdexpro.bean.response;

import java.util.List;

import com.qdexpro.controller.TeardownResponse;

public class TestCaseDetails {

	private TestCaseResponse testCaseResp;
	private SetupResponse testCaseSetupResponse;
	private List<StepDetails> testCaseSetupStepDetails;
	private TeardownResponse testCaseTearDownResponse;
	private List<StepDetails> testCaseTearDownStepDetails;
	private List<StepDetails> testCaseStepDetails;
	
	public List<StepDetails> getTestCaseSetupStepDetails() {
		return testCaseSetupStepDetails;
	}
	public void setTestCaseSetupStepDetails(List<StepDetails> testCaseSetupStepDetails) {
		this.testCaseSetupStepDetails = testCaseSetupStepDetails;
	}
	public TestCaseResponse getTestCaseResp() {
		return testCaseResp;
	}
	public void setTestCaseResp(TestCaseResponse testCaseResp) {
		this.testCaseResp = testCaseResp;
	}
	public SetupResponse getTestCaseSetupResponse() {
		return testCaseSetupResponse;
	}
	public void setTestCaseSetupResponse(SetupResponse testCaseSetupResponse) {
		this.testCaseSetupResponse = testCaseSetupResponse;
	}
	public List<StepDetails> getTestCaseStepDetails() {
		return testCaseStepDetails;
	}
	public void setTestCaseStepDetails(List<StepDetails> testCaseStepDetails) {
		this.testCaseStepDetails = testCaseStepDetails;
	}
	public TeardownResponse getTestCaseTearDownResponse() {
		return testCaseTearDownResponse;
	}
	public void setTestCaseTearDownResponse(TeardownResponse testCaseTearDownResponse) {
		this.testCaseTearDownResponse = testCaseTearDownResponse;
	}
	public List<StepDetails> getTestCaseTearDownStepDetails() {
		return testCaseTearDownStepDetails;
	}
	public void setTestCaseTearDownStepDetails(List<StepDetails> testCaseTearDownStepDetails) {
		this.testCaseTearDownStepDetails = testCaseTearDownStepDetails;
	}
	
	
}
