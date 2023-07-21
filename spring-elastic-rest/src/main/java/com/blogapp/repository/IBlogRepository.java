package com.blogapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;

import com.blogapp.model.Blog;

public interface IBlogRepository {

	String createOrUpdateBlog(Blog blog);
	String deleteBlog(String blogId);
	Blog findById(String blogId);
	List<Blog> findAll();
	Page<Blog> findByAuthor(String author);
	
	
}
