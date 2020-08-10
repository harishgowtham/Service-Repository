package com.qdexpro.bean.response;

public class TestCaseResponse {

	private String id;
	private String testCaseName;
	private String step;
	private String tearDown;
	private String setup;
	public String getTestCaseName() {
		return testCaseName;
	}
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getTearDown() {
		return tearDown;
	}
	public void setTearDown(String tearDown) {
		this.tearDown = tearDown;
	}
	public String getSetup() {
		return setup;
	}
	public void setSetup(String setup) {
		this.setup = setup;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
