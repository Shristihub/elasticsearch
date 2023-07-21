package com.blogapp.model;

public class Topic {
	private Integer topicId;
	private String topicName;
	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Topic(Integer topicId, String topicName) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + "]";
	}
	
	

}
