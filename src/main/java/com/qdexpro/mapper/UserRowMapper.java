package com.qdexpro.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.qdexpro.bean.InputRequestBean;

public class UserRowMapper implements RowMapper<InputRequestBean>{

	@Override
	public InputRequestBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		InputRequestBean userbean =new InputRequestBean();
		userbean.setId(rs.getString("id"));
		userbean.setAge(rs.getString("age"));
		userbean.setName(rs.getString("name"));
		return userbean;
	}

}
