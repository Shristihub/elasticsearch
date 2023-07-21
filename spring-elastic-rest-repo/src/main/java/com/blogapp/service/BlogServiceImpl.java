package com.blogapp.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blogapp.model.Blog;
import com.blogapp.repository.IBlogRepository;

@Service
public class BlogServiceImpl  implements IBlogService{

	@Autowired
	private IBlogRepository blogRepository;
	Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

	@Override
	public Blog addBlog(Blog blog) {
		Blog bl =  blogRepository.save(blog);
		logger.debug("debugging");
		System.out.println(bl);
		return bl;
	}

	@Override
	public void deleteBlog(String blogId) {
		blogRepository.deleteById(blogId);
	}


	@Override
	public Blog getById(String blogId) {
		return blogRepository.findById(blogId).get();
	}

	@Override
	public List<Blog> getAll() {
		Iterable<Blog> blogItr = blogRepository.findAll();
		return StreamSupport.stream(blogItr.spliterator(), true)
							.collect(Collectors.toList());
	}

	@Override
	public Page<Blog> getByAuthor(String author) {
		Pageable page = Pageable.ofSize(2);
		return blogRepository.findByAuthor(author,page);
	}
	

}
