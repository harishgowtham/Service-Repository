package com.qdexpro.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.qdexpro.bean.response.SetupResponse;
import com.qdexpro.bean.response.StepDetails;

public class SetupDetailsMapper implements RowMapper<SetupResponse> {

	@Override
	public SetupResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		SetupResponse response =new SetupResponse();
		response.setId(rs.getString("id"));
		response.setStepList(rs.getString("step"));
		return response;
	}

}
