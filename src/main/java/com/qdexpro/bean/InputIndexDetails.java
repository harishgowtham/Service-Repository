package com.qdexpro.bean;

public class InputIndexDetails {

	private String indexName;
	private String indexType;
	private String indexId;
	private InputJSONDoc inputJSONDoc;
	
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	public InputJSONDoc getInputJSONDoc() {
		return inputJSONDoc;
	}
	public void setInputJSONDoc(InputJSONDoc inputJSONDoc) {
		this.inputJSONDoc = inputJSONDoc;
	}
	
	
}
