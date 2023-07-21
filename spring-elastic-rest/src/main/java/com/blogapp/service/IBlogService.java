package com.blogapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.blogapp.model.Blog;

public interface IBlogService {

	String createOrUpdateBlog(Blog blog);
	void deleteBlog(String blogId);
	Blog getById(String blogId);
	List<Blog> getAll();
	Page<Blog> getByAuthor(String author);

}
