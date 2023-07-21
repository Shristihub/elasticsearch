package com.blogapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.blogapp.model.Blog;
import com.blogapp.repository.IBlogRepository;

@Service
public class BlogServiceImpl  implements IBlogService{

	@Autowired
	private IBlogRepository blogRepository;

	@Override
	public String createOrUpdateBlog(Blog blog) {
		return blogRepository.createOrUpdateBlog(blog);
	}

	@Override
	public void deleteBlog(String blogId) {
		blogRepository.deleteBlog(blogId);
	}


	@Override
	public Blog getById(String blogId) {
		return blogRepository.findById(blogId);
	}

	@Override
	public List<Blog> getAll() {
		return blogRepository.findAll();
	}

	@Override
	public Page<Blog> getByAuthor(String author) {
		return blogRepository.findByAuthor(author);
	}
	

}
