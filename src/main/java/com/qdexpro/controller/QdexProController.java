package com.qdexpro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.qdexpro.QdexProApplication;
import com.qdexpro.bean.InputIndexDetails;
import com.qdexpro.bean.InputRequestBean;
import com.qdexpro.bean.NewProjectRequest;
import com.qdexpro.bean.ScenarioDetailsBean;
import com.qdexpro.bean.StepBean;
import com.qdexpro.bean.TestCaseBean;
import com.qdexpro.bean.response.ScenarioDetailsResponse;
import com.qdexpro.bean.response.ScenarioResponse;
import com.qdexpro.bean.response.SetupResponse;
import com.qdexpro.bean.response.StepDetails;
import com.qdexpro.bean.response.TestCaseDetails;
import com.qdexpro.bean.response.TestCaseResponse;
import com.qdexpro.service.IndexSearchService;
import com.qdexpro.service.QdexProService;

@RestController
public class QdexProController {

	private static final Logger LOGGER = LoggerFactory.getLogger(QdexProController.class);

	@Autowired
	private QdexProService qdexProService;
	
	@Autowired
	private IndexSearchService indexSearchService;

	@RequestMapping(method = RequestMethod.POST, value = "/insertScenarioDetails")
	
	@Transactional
	public String insertScenarioDetails(@RequestBody ScenarioDetailsBean requestDetails, HttpServletResponse reponse,
			HttpServletRequest request) throws Exception {
		LOGGER.info("Into Controller---->");
		// qdexProService.insertData(request.getId(), request.getName(),
		// request.getAge());
		List<StepBean> allStepsList = new ArrayList<StepBean>();
		if (requestDetails.getSetup() != null) {
			allStepsList.addAll(requestDetails.getSetup().getStep());
		}
		if (requestDetails.getTestCase()!= null && requestDetails.getTestCase().size()>0) {
			for (TestCaseBean testCase : requestDetails.getTestCase()) {
				allStepsList.addAll(testCase.getStep());
				allStepsList.addAll(testCase.getSetup().getStep());
				allStepsList.addAll(testCase.getTeardown().getStep());
			}
		}
		if (requestDetails.getTeardown() != null) {
			allStepsList.addAll(requestDetails.getTeardown().getStep());
		}
		
		boolean unique=containsUnique(allStepsList);
		System.out.println("unique" + unique);
		if(unique==false){
			throw new IllegalArgumentException("Duplicate steps added");
		}
		List<StepBean> finalSteps = new ArrayList<StepBean>();
		String stepId="";
		for(StepBean stepFinal:allStepsList){
			String step=qdexProService.readStepDetails(stepFinal);
		if(step==null||step.isEmpty()){
			finalSteps.add(stepFinal);
		}else{
			stepId=stepId+"|"+step;
			System.out.println("Step id values--->"+stepId);
		}
		}
		
		qdexProService.insertStepDetails(finalSteps);

		String scenarioTearDownId = null;
		if (requestDetails.getTeardown() != null) {
		List<StepBean> tearDownSteps = requestDetails.getTeardown().getStep();
		if (tearDownSteps.size() > 0) {
			StringBuilder tearDownId = new StringBuilder();
			String id = "";
			for (StepBean step : tearDownSteps) {
				id = qdexProService.readStepDetails(step);
				tearDownId.append(id + "|");
			}
			System.out.println("setupId--->" + tearDownId);
			qdexProService.insertTearDownDetails(tearDownId.toString());
			scenarioTearDownId = qdexProService.readTearDownId(tearDownId.toString());
		}
		}
		
		String scenarioSetupId = null;
		if (requestDetails.getSetup() != null) {
		List<StepBean> setupSteps = requestDetails.getSetup().getStep();
		if (setupSteps.size() > 0) {
			StringBuilder setupId = new StringBuilder();
			String id = "";
			for (StepBean step : setupSteps) {
				id = qdexProService.readStepDetails(step);
				setupId.append(id + "|");
			}
			System.out.println("setupId--->" + setupId);
			qdexProService.insertSetupDetails(setupId.toString());
			scenarioSetupId = qdexProService.readSetupId(setupId.toString());
		}
		}

		StringBuilder scenarioTestCaseid = null;
		if (requestDetails.getTestCase()!= null && requestDetails.getTestCase().size()>0) {
			scenarioTestCaseid = new StringBuilder();
		List<TestCaseBean> testCaseList = requestDetails.getTestCase();
		if (testCaseList.size() > 0) {
			for (TestCaseBean testCase : testCaseList) {

				List<StepBean> testCaseTearDownSteps = testCase.getTeardown().getStep();
				String tearDownResponseId = "";
				if (testCaseTearDownSteps.size() > 0) {
					StringBuilder tearDownId = new StringBuilder();
					String id = "";
					for (StepBean step : testCaseTearDownSteps) {
						id = qdexProService.readStepDetails(step);
						tearDownId.append(id + "|");
					}
					qdexProService.insertTearDownDetails(tearDownId.toString());
					tearDownResponseId = qdexProService.readTearDownId(tearDownId.toString());
					System.out.println("tearDownResponseId--->" + tearDownResponseId);
				}

				List<StepBean> testCaseSetupSteps = testCase.getSetup().getStep();
				String setupResponseId = "";
				if (testCaseSetupSteps.size() > 0) {
					StringBuilder setupId = new StringBuilder();
					String id = "";
					for (StepBean step : testCaseSetupSteps) {
						id = qdexProService.readStepDetails(step);
						setupId.append(id + "|");
					}
					System.out.println("setupId--->" + setupId);
					qdexProService.insertSetupDetails(setupId.toString());
					setupResponseId = qdexProService.readSetupId(setupId.toString());
					System.out.println("tearDownResponseId--->" + setupResponseId);
				}

				List<StepBean> testCaseSteps = testCase.getStep();
				String caseId = "";
				if (testCaseSteps.size() > 0) {
					StringBuilder testCaseId = new StringBuilder();
					String id = "";
					for (StepBean step : testCaseSteps) {
						id = qdexProService.readStepDetails(step);
						testCaseId.append(id + "|");
					}
					System.out.println("setupId--->" + testCaseId);
					qdexProService.insertTestCaseDetails(testCase.getTestCaseName(), testCaseId.toString(),
							tearDownResponseId, setupResponseId);
					caseId = qdexProService.readTestCaseId(testCase.getTestCaseName());
					scenarioTestCaseid.append(caseId + "|");

				}
			}
		}
		}
		if(scenarioTestCaseid!=null){
		qdexProService.insertScenarioDetails(requestDetails.getScenarioName(), scenarioSetupId, scenarioTearDownId,
				scenarioTestCaseid.toString());
		}
		else{
			qdexProService.insertScenarioDetails(requestDetails.getScenarioName(), scenarioSetupId, scenarioTearDownId,
					null);
		}
		LOGGER.info("total Steps" + allStepsList.size());
		reponse.setStatus(201);
		String scenarioId=qdexProService.readScenarioId(requestDetails.getScenarioName());
		return scenarioId;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/readScenarioDetails")
	public String insertScenarioDetails(@RequestParam("scenarioId") int id, HttpServletResponse reponse,
			HttpServletRequest request) throws Exception {

		ScenarioDetailsResponse completeResponse=new ScenarioDetailsResponse();
		
		ScenarioResponse scenario=qdexProService.readScenarioDetails(id);
		completeResponse.setScenarioResponse(scenario);
		SetupResponse scenarioSetup=qdexProService.readSetupDetails(scenario.getSetup());
		completeResponse.setSetupResponse(scenarioSetup);
		List<StepDetails> scenarioStepList=new ArrayList<StepDetails>();
		System.out.println("scenarioSetup"+scenarioSetup);
		String[] setupId=scenarioSetup.getStepList().split("\\|");
		for(String idVal:setupId){
			System.out.println("Setup Id values"+idVal);
			StepDetails stepDetail=new StepDetails();
			stepDetail=qdexProService.readStepCompleteDetails(idVal);
			scenarioStepList.add(stepDetail);
		}
		completeResponse.setSetupStepDetails(scenarioStepList);
		
		TeardownResponse scenarioTeardown=qdexProService.readTearDownDetails(scenario.getTearDown());
		completeResponse.setTeardownResponse(scenarioTeardown);
		
		List<StepDetails> scenarioTeardownStepList=new ArrayList<StepDetails>();
		System.out.println("scenarioSetup"+scenarioSetup);
		String[] teardownId=scenarioTeardown.getStepList().split("\\|");
		for(String idVal:teardownId){
			System.out.println("Teardown Id values"+idVal);
			StepDetails stepDetail=new StepDetails();
			stepDetail=qdexProService.readStepCompleteDetails(idVal);
			scenarioTeardownStepList.add(stepDetail);
		}
		completeResponse.setTearDownStepDetails(scenarioTeardownStepList);
		
		List<TestCaseDetails> testCaseAllDetails=new ArrayList<TestCaseDetails>();
		String[] testCaseIdList=scenario.getTestCase().split("\\|");
		for(String idVal:testCaseIdList){
			TestCaseDetails testCaseBean=new TestCaseDetails();
			TestCaseResponse testCaseDetails= new TestCaseResponse();
			testCaseDetails=qdexProService.readTestCaseDetails(idVal);
			testCaseBean.setTestCaseResp(testCaseDetails);
			
			SetupResponse testCaseSetup=qdexProService.readSetupDetails(testCaseDetails.getSetup());
			testCaseBean.setTestCaseSetupResponse(testCaseSetup);
			
			List<StepDetails> testCaseSetupStepList=new ArrayList<StepDetails>();
			String[] testCaseSetupId=testCaseSetup.getStepList().split("\\|");
			for(String testCaseSetupStepId:testCaseSetupId){
				System.out.println("Setup Id values"+idVal);
				StepDetails stepDetail=new StepDetails();
				stepDetail=qdexProService.readStepCompleteDetails(testCaseSetupStepId);
				testCaseSetupStepList.add(stepDetail);
			}
			testCaseBean.setTestCaseSetupStepDetails(testCaseSetupStepList);
			
			
			TeardownResponse testCaseTeardown=qdexProService.readTearDownDetails(testCaseDetails.getTearDown());
			testCaseBean.setTestCaseTearDownResponse(testCaseTeardown);
			
			List<StepDetails> testCaseTeardownStepList=new ArrayList<StepDetails>();
			String[] testCaseTeardownStepIdList=testCaseTeardown.getStepList().split("\\|");
			for(String testCaseTeardownStepId:testCaseTeardownStepIdList){
				System.out.println("Teardown Id values"+testCaseTeardownStepId);
				StepDetails stepDetail=new StepDetails();
				stepDetail=qdexProService.readStepCompleteDetails(testCaseTeardownStepId);
				testCaseTeardownStepList.add(stepDetail);
			}
			testCaseBean.setTestCaseTearDownStepDetails(testCaseTeardownStepList);
			
			List<StepDetails> testCaseBaseSteps=new ArrayList<StepDetails>();
			String[] testCaseBaseStepList=testCaseDetails.getStep().split("\\|");
			for(String testCaseBaseStepId:testCaseBaseStepList){
				System.out.println("Setup Id values"+idVal);
				StepDetails stepDetail=new StepDetails();
				stepDetail=qdexProService.readStepCompleteDetails(testCaseBaseStepId);
				testCaseBaseSteps.add(stepDetail);
			}
			testCaseBean.setTestCaseStepDetails(testCaseBaseSteps);
			testCaseAllDetails.add(testCaseBean);
		}
		completeResponse.setTestCaseDetails(testCaseAllDetails);
		Gson gson=new Gson();
		
		return gson.toJson(completeResponse);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/createNewProject")
	public String createNewProject(@RequestBody NewProjectRequest request, HttpServletResponse reponse)
			throws Exception {
		LOGGER.info("Into Controller---->");
		qdexProService.createNewProject(request.getProjName());
		String responseValue = "";
		if (reponse.getStatus() == 200) {
			responseValue = "Success";
		} else {
			responseValue = "failure";
		}
		return responseValue;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/user/updateDetails")
	public String updateUserDetails(@RequestBody InputRequestBean request, HttpServletResponse reponse)
			throws Exception {
		LOGGER.info("Into Controller---->");
		qdexProService.updateDetails(request.getId(), request.getName(), request.getAge());
		String responseValue = "";
		if (reponse.getStatus() == 200) {
			responseValue = "Success";
		} else {
			responseValue = "failure";
		}
		return responseValue;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user/deleteDetails")
	public String deleteUserDetails(@RequestBody InputRequestBean request, HttpServletResponse reponse)
			throws Exception {
		LOGGER.info("Into Controller---->");
		qdexProService.deleteData(request.getId());
		String responseValue = "";
		if (reponse.getStatus() == 200) {
			responseValue = "Success";
		} else {
			responseValue = "failure";
		}
		return responseValue;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/readAllDetails")
	public List<InputRequestBean> readAllDetails(HttpServletResponse reponse) {
		LOGGER.info("Into Controller---->");
		List<InputRequestBean> userList = new ArrayList<InputRequestBean>();
		userList = qdexProService.readAllDetails();
		return userList;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/inserContent")
	public String inserContent(@RequestParam("uuid") String uuid, HttpServletResponse reponse) throws Exception {
		LOGGER.info("Into Controller---->");
		qdexProService.inserContent(uuid);
		String responseValue = "";
		if (reponse.getStatus() == 200) {
			responseValue = "Success";
		} else {
			responseValue = "failure";
		}
		return responseValue;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/readContent")
	public String readContent(@RequestParam("uuid") String uuid, HttpServletResponse reponse) throws Exception {
		LOGGER.info("Into Controller---->");
		String content = qdexProService.readContent(uuid);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("content", content);
		String response = jsonObject.toString();
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/insertJSONDocument")
	public void insertJSONDocument(@RequestBody InputIndexDetails indexDetails, HttpServletResponse response) throws Exception {
		LOGGER.info("Into Controller---->");
		indexSearchService.insertJSONDocument(indexDetails);
		
	}
	
	public static <T> boolean containsUnique(List<T> list){
	    return list.stream().allMatch(new HashSet<>()::add);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public void IllegalArgumentException(IllegalArgumentException ex, HttpServletResponse response){
		try {
			response.sendError(400, ex.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
