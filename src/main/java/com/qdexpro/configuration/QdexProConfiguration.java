package com.qdexpro.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.qdexpro.dao.QdexProDAO;
import com.qdexpro.service.QdexProService;

@Configuration
@EnableAutoConfiguration
public class QdexProConfiguration {

	@Bean
    public QdexProService getQdexProService() {
        return new QdexProService();
    }
	
	@Bean
    public QdexProDAO getQdexProDAO() {
        return new QdexProDAO();
    }
}
