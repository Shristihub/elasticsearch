package com.blogapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "blogs")
public class Blog {
	private String title;
	@Field(value = "author")
	private String author; // health, computers,
	@Id
	private String blogId;
	
	private String content;
	@Field(value="topic",type=FieldType.Nested,includeInParent = true)
	private List<Topic> topic; // java, computers
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<Topic> getTopic() {
		return topic;
	}
	public void setTopic(List<Topic> topic) {
		this.topic = topic;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Blog [title=" + title + ", author=" + author + ", blogId=" + blogId + ", content=" + content + ", topic="
				+ topic + "]";
	}
	
	

	
}
