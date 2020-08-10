package com.qdexpro.bean;

public class StepBean {

	private String stepName;
	private String idType;
	private String controlType;
	private String waitTime;
	
	
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getControlType() {
		return controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	public String getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(String waitTime) {
		this.waitTime = waitTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((controlType == null) ? 0 : controlType.hashCode());
		result = prime * result + ((idType == null) ? 0 : idType.hashCode());
		result = prime * result + ((stepName == null) ? 0 : stepName.hashCode());
		result = prime * result + ((waitTime == null) ? 0 : waitTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StepBean other = (StepBean) obj;
		if (controlType == null) {
			if (other.controlType != null)
				return false;
		} else if (!controlType.equals(other.controlType))
			return false;
		if (idType == null) {
			if (other.idType != null)
				return false;
		} else if (!idType.equals(other.idType))
			return false;
		if (stepName == null) {
			if (other.stepName != null)
				return false;
		} else if (!stepName.equals(other.stepName))
			return false;
		if (waitTime == null) {
			if (other.waitTime != null)
				return false;
		} else if (!waitTime.equals(other.waitTime))
			return false;
		return true;
	}
	
	 
}
