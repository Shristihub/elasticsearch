package com.blogapp.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("es")
//@EnableElasticsearchRepositories(basePackages = { "com.blogapp.repository" })
public class BlogConfig {
	
	
	String username;
	String password;
	String hostName;
	int port;
	// Client that connects to an Elasticsearch cluster through HTTP.
	@Bean
	public RestClient getRestClient() {
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("shristi", "shristi123"));
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200))
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        // Create the low-level client
        RestClient restClient = builder.build();
        return restClient;
	}
	// transport layer to map model to json
	@Bean
	public ElasticsearchTransport getTransport() {
		return new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
	}
	// for querying elasticsearch
	@Bean
	public ElasticsearchClient elasticSearchClient() {
		return new ElasticsearchClient(getTransport());
	}
	
}
