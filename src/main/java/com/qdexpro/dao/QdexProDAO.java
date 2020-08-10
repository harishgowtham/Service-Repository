package com.qdexpro.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qdexpro.bean.InputRequestBean;
import com.qdexpro.bean.StepBean;
import com.qdexpro.bean.response.ScenarioResponse;
import com.qdexpro.bean.response.SetupResponse;
import com.qdexpro.bean.response.StepDetails;
import com.qdexpro.bean.response.TestCaseResponse;
import com.qdexpro.controller.TeardownResponse;
import com.qdexpro.mapper.ScenarioDetailsRowMapper;
import com.qdexpro.mapper.SetupDetailsMapper;
import com.qdexpro.mapper.StepDetailsMapper;
import com.qdexpro.mapper.TearDownDetailsMapper;
import com.qdexpro.mapper.TestCaseDetailsMapper;
import com.qdexpro.mapper.UserRowMapper;

@Repository
public class QdexProDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(QdexProDAO.class);
	private static final String INSERT_DATA = "INSERT INTO emp VALUES(?,?,?);";
	private static final String UPDATE_DATA = "UPDATE emp SET name=?,age=? where id=?;";
	private static final String DELETE_DATA = "DELETE from emp where id=?;";
	private static final String READ_ALL_DATA = "SELECT * FROM emp;";
	private static final String INSERT_CONTENT = "INSERT INTO content VALUES(?,?);";
	private static final String READ_CONTENT_FOR_UUID = "SELECT content from content where id=?;";

	private static final String INSERT_STEP_DETAILS = "INSERT INTO step (step_name,id_type,control_type,wait_time) VALUES(?,?,?,?);";
	private static final String READ_STEP_DETAILS = "SELECT id from step where step_name=? AND id_type=? AND control_type=?";
	private static final String INSERT_TEAR_DOWN_DATA = "INSERT INTO tear_down(step) VALUES(?);";
	private static final String INSERT_SETUP_DATA = "INSERT INTO setup(step) VALUES(?);";
	private static final String READ_TEAR_DOWN_ID = "SELECT id from tear_down where step=?;" ;
	private static final String READ_SETUP_ID = "SELECT id from setup where step=?;" ;
	private static final String INSERT_TEST_CASE_DETAILS = "INSERT INTO test_case (test_case_name,step,tear_down,setup) VALUES(?,?,?,?);";
	private static final String INSERT_SCENARIO_DETAILS = "INSERT INTO scenario (scenario_name,setup,tear_down,test_case) VALUES(?,?,?,?);";
	private static final String READ_TEST_CASE_ID = "SELECT id from test_case WHERE test_case_name=?;";
	private static final String READ_SCENARIO_DETAILS = "Select * from scenario where id = ?;";
	private static final String READ_SETUP_DETAILS = "SELECT * from setup where id=?;";
	private static final String READ_STEP_COMPLETE_DETAILS = "Select * from step where id=?;";
	private static final String READ_TEARDOWN_DETAILS = "SELECT * from tear_down where id=?;";
	private static final String READ_TEST_CASE_DETAILS = "SELECT * from test_case where id=?;";
	private static final String READ_SCENARIO_ID = "Select id from scenario where scenario_name = ?;";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertData(String id, String name, String age) throws Exception {
		LOGGER.info("input values" + id + name + age);
		Object[] params = { id, name, age };
		try {
			jdbcTemplate.update(INSERT_DATA, params);
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
	}

	public void updateDetails(String id, String name, String age) throws Exception {
		LOGGER.info("input values" + id + name + age);
		Object[] params = { name, age, id };
		try {
			jdbcTemplate.update(UPDATE_DATA, params);
		} catch (Exception ex) {
			LOGGER.error("Exception while updating details" + ex);
			throw ex;
		}
	}

	public void deleteData(String id) throws Exception {
		LOGGER.info("input values" + id);
		Object[] params = { id };
		try {
			jdbcTemplate.update(DELETE_DATA, params);
		} catch (Exception ex) {
			LOGGER.error("Exception while deleting details" + ex);
			throw ex;
		}
	}

	public List<InputRequestBean> readAllDetails() {
		List<InputRequestBean> userList = new ArrayList<InputRequestBean>();
		try {
			userList = jdbcTemplate.query(READ_ALL_DATA, new UserRowMapper());
		} catch (Exception ex) {
			LOGGER.error("Exception while deleting details" + ex);
			throw ex;
		}
		return userList;
	}

	public void inserContent(String uuid, String content) {
		Object[] params = { uuid, content };
		try {
			jdbcTemplate.update(INSERT_CONTENT, params);
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
	}

	public String readContent(String uuid) {
		String content = "";
		Object[] params = { uuid };
		try {
			content = jdbcTemplate.queryForObject(READ_CONTENT_FOR_UUID, params, String.class);
		} catch (Exception ex) {
			LOGGER.error("Exception while deleting details" + ex);
			throw ex;
		}
		return content;
	}

	public void insertStepDetails(List<StepBean> allStepsList) {
		// TODO Auto-generated method stub
		for (StepBean step : allStepsList) {
			Object[] params = { step.getStepName(), step.getIdType(), step.getControlType(), step.getWaitTime() };
			try {
				jdbcTemplate.update(INSERT_STEP_DETAILS, params);
			} catch (Exception ex) {
				LOGGER.error("Exception while inserting details" + ex);
				throw ex;
			}
		}

	}

	public String readStepDetails(StepBean step) {
		Object[] params = { step.getStepName(), step.getIdType(), step.getControlType() };
		String id = "";
		try {
			id = jdbcTemplate.queryForObject(READ_STEP_DETAILS, params, String.class);
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
		return id;
	}

	public void insertTearDownDetails(String tearDownId) {
		LOGGER.info("input values" + tearDownId);
		Object[] params = { tearDownId };
		try {
			jdbcTemplate.update(INSERT_TEAR_DOWN_DATA, params);
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
	}

	public void insertSetupDetails(String setupId) {
		LOGGER.info("input values" + setupId);
		Object[] params = { setupId };
		try {
			jdbcTemplate.update(INSERT_SETUP_DATA, params);
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
	}

	public String readTearDownId(String tearDownId) {
		Object[] params = { tearDownId};
		String id = "";
		try {
			id = jdbcTemplate.queryForObject(READ_TEAR_DOWN_ID, params, String.class);
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
		return id;
	}

	public String readSetupId(String setupId) {
		Object[] params = { setupId};
		String id = "";
		try {
			id = jdbcTemplate.queryForObject(READ_SETUP_ID, params, String.class);
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
		return id;
	}

	public void insertTestCaseDetails(String testCaseName, String steps, String tearDownResponseId,
			String setupResponseId) {
		LOGGER.info("input values" + testCaseName);
		Object[] params = { testCaseName, steps,tearDownResponseId,setupResponseId};
		try {
			jdbcTemplate.update(INSERT_TEST_CASE_DETAILS, params);
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
	}

	public void insertScenarioDetails(String scenarioName, String scenarioSetupId, String scenarioTearDownId,
			String testCaseId) {
		LOGGER.info("input values" + scenarioName);
		Object[] params = { scenarioName, scenarioSetupId,scenarioTearDownId,testCaseId};
		try {
			jdbcTemplate.update(INSERT_SCENARIO_DETAILS, params);
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
	}

	public String readTestCaseId(String testCaseName) {
		Object[] params = { testCaseName};
		String id = "";
		try {
			id = jdbcTemplate.queryForObject(READ_TEST_CASE_ID, params, String.class);
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
		return id;
	}

	public ScenarioResponse readScenarioDetails(int id) {
		ScenarioResponse scenarioResponse = null;
		Object[] params = { id};
		try {
			scenarioResponse = jdbcTemplate.queryForObject(READ_SCENARIO_DETAILS,params, new ScenarioDetailsRowMapper());
		} catch (Exception ex) {
			LOGGER.error("Exception while deleting details" + ex);
			throw ex;
		}
		return scenarioResponse;
	}

	public SetupResponse readSetupDetails(String setupId) {
		Object[] params = { setupId};
		SetupResponse stepId = null;
		try {
			stepId = jdbcTemplate.queryForObject(READ_SETUP_DETAILS, params, new SetupDetailsMapper());
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
		return stepId;
	}

	public StepDetails readStepCompleteDetails(String id) {
		Object[] params = { id};
		StepDetails stepDetails = null;
		try {
			stepDetails = jdbcTemplate.queryForObject(READ_STEP_COMPLETE_DETAILS, params, new StepDetailsMapper());
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
		return stepDetails;
	}

	public TeardownResponse readTearDownDetails(String tearDownId) {
		Object[] params = { tearDownId};
		TeardownResponse stepId = null;
		try {
			stepId = jdbcTemplate.queryForObject(READ_TEARDOWN_DETAILS, params, new TearDownDetailsMapper());
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
		return stepId;
	}

	public TestCaseResponse readTestCaseDetails(String idVal) {
		Object[] params = { idVal};
		TestCaseResponse testCaseDetails = null;
		try {
			testCaseDetails = jdbcTemplate.queryForObject(READ_TEST_CASE_DETAILS, params, new TestCaseDetailsMapper());
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
		return testCaseDetails;
	}

	public String readScenarioId(String scenarioName) {
		Object[] params = { scenarioName};
		String id = "";
		try {
			id = jdbcTemplate.queryForObject(READ_SCENARIO_ID, params, String.class);
		} catch (Exception ex) {
			LOGGER.error("Exception while inserting details" + ex);
			throw ex;
		}
		return id;
	}

}
