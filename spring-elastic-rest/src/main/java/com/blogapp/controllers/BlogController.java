package com.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.model.Blog;
import com.blogapp.service.IBlogService;

@RestController
@RequestMapping("/blog-api")
public class BlogController {
	
	@Autowired
	private IBlogService blogService;
	
	@PostMapping("/blogs")
	public String createOrUpdateBlog(@RequestBody Blog blog) {
		return blogService.createOrUpdateBlog(blog);
	}
	@DeleteMapping("/blogs/blogId/{blogId}")
	public void deleteBlog(@PathVariable("blogId") String blogId) {
		blogService.deleteBlog(blogId);
	}
	@GetMapping("/blogs/blogId/{blogId}")
	public Blog findById(@PathVariable("blogId")String blogId) {
		return blogService.getById(blogId);
	}
	@GetMapping("/blogs")
	public List<Blog> findAll(){
		return blogService.getAll();
		
	}
	@GetMapping("/blogs/author/{author}")
	public Page<Blog> findByAuthor(@PathVariable("author")String author){
		return null;
	}
}
