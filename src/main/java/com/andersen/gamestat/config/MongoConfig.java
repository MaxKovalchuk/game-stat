package com.andersen.gamestat.config;

import com.andersen.gamestat.repository.EndGameRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = EndGameRepository.class)
@EnableMongoAuditing
public class MongoConfig {
}
