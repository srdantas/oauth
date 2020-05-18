package com.data.science.net.oauth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
    basePackages = "com.data.science.net.oauth.gateway.database.mongo.repository")
public class MongoConfiguration {}
