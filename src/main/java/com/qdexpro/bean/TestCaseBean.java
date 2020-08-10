package com.qdexpro.bean;

import java.util.List;

public class TestCaseBean {

	private String testCaseName;
	private SetupBean setup;
	private List<StepBean> step;
	private TearDown teardown;
	
	public SetupBean getSetup() {
		return setup;
	}
	public void setSetup(SetupBean setup) {
		this.setup = setup;
	}
	public List<StepBean> getStep() {
		return step;
	}
	public void setStep(List<StepBean> step) {
		this.step = step;
	}
	public TearDown getTeardown() {
		return teardown;
	}
	public void setTeardown(TearDown teardown) {
		this.teardown = teardown;
	}
	public String getTestCaseName() {
		return testCaseName;
	}
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	
	
}
