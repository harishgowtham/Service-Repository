package com.qdexpro.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.qdexpro.bean.response.ScenarioResponse;
import com.qdexpro.bean.response.TestCaseResponse;

public class TestCaseDetailsMapper implements RowMapper<TestCaseResponse> {


	@Override
	public TestCaseResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		TestCaseResponse response =new TestCaseResponse();
		response.setId(rs.getString("id"));
		response.setTestCaseName(rs.getString("test_case_name"));
		response.setSetup(rs.getString("setup"));
		response.setTearDown(rs.getString("tear_down"));
		response.setStep(rs.getString("step"));
		return response;
	}

	


}
