package com.blogapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.blogapp.model.Blog;

@Repository
public interface IBlogRepository extends ElasticsearchRepository<Blog, String>{

	
	Page<Blog> findByAuthor(String author,Pageable page);
	
}
