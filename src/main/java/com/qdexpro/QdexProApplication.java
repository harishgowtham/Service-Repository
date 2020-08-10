package com.qdexpro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;




@SpringBootApplication(scanBasePackages = {"com.qdexpro"})
public class QdexProApplication extends SpringBootServletInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(QdexProApplication.class);

    public static void main(String[] args) throws Exception {
    	LOGGER.info("QDEX PRO Application ---->");
    	System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "|{}[]");
        SpringApplication.run(QdexProApplication.class, args);
        LOGGER.info("Application started successfully");
    }
    
}