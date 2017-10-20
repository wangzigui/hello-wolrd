package com;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {
	
	@Bean(name = "fmdcDataSource")
	@Qualifier("fmdcDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.fmdc")
	public DataSource fmdcDataSource() {
		return DataSourceBuilder.create().build();
	}
	
    @Bean(name = "fmdcJdbcTemplate")  
    public JdbcTemplate primaryJdbcTemplate(  
            @Qualifier("fmdcDataSource") DataSource dataSource) {  
        return new JdbcTemplate(dataSource);  
    }  

	@Bean(name = "windDataSource")
	@Qualifier("windDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.wind")
	public DataSource windDataSource() {
		return DataSourceBuilder.create().build();
	}

    @Bean(name = "windJdbcTemplate")  
    public JdbcTemplate secondaryJdbcTemplate(  
            @Qualifier("windDataSource") DataSource dataSource) {  
        return new JdbcTemplate(dataSource);  
    }

	@Bean(name = "rdiDataSource")
	@Qualifier("rdiDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.rdi")
	public DataSource rdiDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "rdiJdbcTemplate")
	public JdbcTemplate rdiJdbcTemplate(
			@Qualifier("rdiDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

    @Bean(name = "fmpDataSource")
  	@Qualifier("fmpDataSource")
  	@ConfigurationProperties(prefix = "spring.datasource.fmp")
  	public DataSource fmpDataSource() {
  		return DataSourceBuilder.create().build();
  	}

	@Bean(name = "fmpJdbcTemplate")
	public JdbcTemplate fmpJdbcTemplate(
			@Qualifier("fmpDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
    
}
