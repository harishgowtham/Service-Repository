package com.qdexpro.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;



@Repository
public class QdexProDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(QdexProDAO.class);
	private static final String INSERT_DATA="INSERT INTO emp VALUES(?,?,?);";
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	public void insertData(String id, String name, String age) {
		LOGGER.info("input values"+id+name+age);
		Object[] params={id,name,age};
		LOGGER.info("params"+params.length);
		jdbcTemplate.update(INSERT_DATA, params);
		}
    

}
