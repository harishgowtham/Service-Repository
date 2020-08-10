package com.qdexpro.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.qdexpro.bean.response.SetupResponse;
import com.qdexpro.controller.TeardownResponse;

public class TearDownDetailsMapper implements RowMapper<TeardownResponse> {

	@Override
	public TeardownResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		TeardownResponse response = new TeardownResponse();
		response.setId(rs.getString("id"));
		response.setStepList(rs.getString("step"));
		return response;
	}
}
