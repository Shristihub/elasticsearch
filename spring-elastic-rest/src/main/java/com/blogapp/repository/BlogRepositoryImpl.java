package com.blogapp.repository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.blogapp.model.Blog;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

@Repository
public class BlogRepositoryImpl implements IBlogRepository {

	// using elasticSearchClient
	@Autowired
	private ElasticsearchClient elasticsearchClient;

	private String indexName = "blogs";

	@Override
	public String createOrUpdateBlog(Blog blog) {
		// this returns a IndexResponse object
		IndexResponse response = null;
		try {
			response = elasticsearchClient.index(
					// this returns a IndexRequest Object
					doc -> doc.index(indexName).id(blog.getBlogId()).document(blog) // accepts the request body
			);
			System.out.println(response.result().name());
		} catch (ElasticsearchException | IOException e) {
			e.printStackTrace();
		}
		// check if id exists
		if (response.result().name().equals("Created")) {
			return new StringBuilder("CREATED").toString();
		}else if (response.result().name().equals("Updated")) {
			return new StringBuilder("updated").toString();
		} else {
			return new StringBuilder("error").toString();
		}
	}

	@Override
	public String deleteBlog(String blogId) {
		DeleteRequest request = DeleteRequest.of(doc -> doc.index(indexName).id(blogId));
		DeleteResponse response = null;
		try {
			response = elasticsearchClient.delete(request);
		} catch (ElasticsearchException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (response.result().name().equals("deleted"))
			System.out.println("document deleted");
		   if (Objects.nonNull(response.result()) && 
				   !response.result().name().equals("NotFound")) {
	            return new StringBuilder("Product with id " + response.id() + " has been deleted.").toString();
	        }
	        System.out.println("Product not found");
	        return new StringBuilder("Product with id " + response.id()+" does not exist.").toString();

	}

	@Override
	public Blog findById(String blogId) {
		Blog blog = null;
		try {
			GetResponse<Blog> response = elasticsearchClient.get(doc -> doc.index(indexName).id(blogId), Blog.class);
			if (response.found()) {
				blog = response.source();
			}
		} catch (ElasticsearchException | IOException e) {
			e.printStackTrace();
		}
		return blog;
	}

	@Override
	public List<Blog> findAll() {
		List<Blog> blogs = null;
		// like a query
		SearchRequest request = SearchRequest.of(doc -> doc.index(indexName));
		try {
			SearchResponse<Blog> response = elasticsearchClient.search(request, Blog.class);
			// returns metadata and then list of hits of blogs
			List<Hit<Blog>> blogHits = response.hits().hits();
			blogs = blogHits.stream().map(hitObj -> hitObj.source()).collect(Collectors.toList());
		} catch (ElasticsearchException | IOException e) {
			e.printStackTrace();
		}
		return blogs;
	}

	@Override
	public Page<Blog> findByAuthor(String author) {
		// create the request
//		SearchRequest request = SearchRequest.of(doc->doc.index(indexName).q);

		return null;
	}

}
