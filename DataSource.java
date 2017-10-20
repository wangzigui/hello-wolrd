package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataSource {
	
    public static JdbcTemplate fmdc;
	
    public static JdbcTemplate wind;

    public static JdbcTemplate rdi;
    
    public static JdbcTemplate fmp;
    
	@Autowired(required = true)
	public void setFMDCDataSource(@Qualifier("fmdcJdbcTemplate") JdbcTemplate jdbcTemplate) {
		DataSource.fmdc = jdbcTemplate;
	}

	@Autowired(required = true)
	public void setWindDataSource(@Qualifier("windJdbcTemplate") JdbcTemplate jdbcTemplate) {
		DataSource.wind = jdbcTemplate;
	}

	@Autowired(required = true)
	public void setFmpDataSource(@Qualifier("fmpJdbcTemplate") JdbcTemplate jdbcTemplate) {
		DataSource.fmp = jdbcTemplate;
	}

	@Autowired(required = true)
	public void setRDIDataSource(@Qualifier("rdiJdbcTemplate") JdbcTemplate jdbcTemplate) {
		DataSource.rdi = jdbcTemplate;
	}
}
