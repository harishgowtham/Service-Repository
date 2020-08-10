package com.qdexpro.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.qdexpro.bean.response.ScenarioResponse;
import com.qdexpro.bean.response.StepDetails;

public class StepDetailsMapper implements RowMapper<StepDetails> {

	@Override
	public StepDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		StepDetails response =new StepDetails();
		response.setStepId(rs.getString("id"));
		response.setStepName(rs.getString("step_name"));
		response.setIdType(rs.getString("id_type"));
		response.setControlType(rs.getString("control_type"));
		response.setWaitTime(rs.getString("wait_time"));
		return response;
	}

}
