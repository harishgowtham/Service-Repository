package com.qdexpro.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.qdexpro.dao.QdexProDAO;
import com.qdexpro.service.IndexSearchService;
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

	 @Bean
     @ConfigurationProperties(prefix = "spring.datasource")
     public DataSource primaryDataSource() {
             return DataSourceBuilder.create().build();
     }

     @Bean
     public JdbcTemplate getJdbcTemplate() {
             return new JdbcTemplate(primaryDataSource());
     }

     /*@Bean
     public ConfigurableServletWebServerFactory webServerFactory() {
         TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
         factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
             @Override
             public void customize(Connector connector) {
                 connector.setProperty("relaxedQueryChars", "|{}[]");
             }
         });
         return factory;
     }
*/
     @Bean
     public IndexSearchService getIndexSearchService() {
         return new IndexSearchService();
     }
     
}
