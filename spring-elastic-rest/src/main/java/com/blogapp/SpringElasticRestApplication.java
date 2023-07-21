package com.blogapp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.blogapp.model.Blog;
import com.blogapp.model.Topic;
import com.blogapp.service.IBlogService;

@SpringBootApplication
public class SpringElasticRestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringElasticRestApplication.class, args);
	}

	@Autowired
	private IBlogService blogService;
	@Override
	public void run(String... args) throws Exception {
		Blog blog = new Blog();
		blog.setBlogId("B001");
		blog.setAuthor("Sri");
		blog.setTitle("Java 8 features");
		blog.setContent("Java 8 has introduced lambda expressions");
		blog.setTopic(Arrays.asList(new Topic(1,"Java"), new Topic(2,"Java 8")));
//		blogService.createOrUpdateBlog(new Blog());
	}

}
