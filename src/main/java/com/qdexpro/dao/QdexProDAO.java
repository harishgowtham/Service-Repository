package com.qdexpro.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class QdexProDAO {

	private static final String INSERT_DATA="INSERT INTO EMP VALUES(?,?,?);";
	
    private JdbcTemplate jdbcTemplate;

	public void insertData(String id, String name, String age) {
		// TODO Auto-generated method stub
		Object[] params={id,name,age};
		jdbcTemplate.update(INSERT_DATA, params);
	}
    

}
