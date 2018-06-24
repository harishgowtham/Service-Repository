package com.qdexpro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication(scanBasePackages = {"com.qdexpro"})
public class QdexProApplication  {
	private static final Logger LOGGER = LoggerFactory.getLogger(QdexProApplication.class);

    public static void main(String[] args) throws Exception {
    	LOGGER.info("QDEX PRO Application ---->");
        SpringApplication.run(QdexProApplication.class, args);
        LOGGER.info("Application started successfully");
    }
    
}