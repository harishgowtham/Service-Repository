package com.qdexpro.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.qdexpro.bean.InputRequestBean;
import com.qdexpro.bean.response.ScenarioResponse;

public class ScenarioDetailsRowMapper implements RowMapper<ScenarioResponse> {

	@Override
	public ScenarioResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		ScenarioResponse response =new ScenarioResponse();
		response.setScenarioId(rs.getString("id"));
		response.setScenarioName(rs.getString("scenario_name"));
		response.setSetup(rs.getString("setup"));
		response.setTearDown(rs.getString("tear_down"));
		response.setTestCase(rs.getString("test_case"));
		return response;
	}

	
}
