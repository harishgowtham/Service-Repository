package com.qdexpro.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdexpro.bean.InputRequestBean;
import com.qdexpro.bean.StepBean;
import com.qdexpro.bean.response.ScenarioResponse;
import com.qdexpro.bean.response.SetupResponse;
import com.qdexpro.bean.response.StepDetails;
import com.qdexpro.bean.response.TestCaseResponse;
import com.qdexpro.controller.TeardownResponse;
import com.qdexpro.dao.QdexProDAO;

@Service
public class QdexProService {

	@Autowired
	private QdexProDAO qdexProDAO;

	public void insertData(String id, String name, String age) throws Exception {
		qdexProDAO.insertData(id, name, age);
	}

	public void updateDetails(String id, String name, String age) throws Exception {
		// TODO Auto-generated method stub
		qdexProDAO.updateDetails(id, name, age);
	}

	public void deleteData(String id) throws Exception {
		// TODO Auto-generated method stub
		qdexProDAO.deleteData(id);
	}

	public List<InputRequestBean> readAllDetails() {
		// TODO Auto-generated method stub
		List<InputRequestBean> userList = new ArrayList<InputRequestBean>();
		userList = qdexProDAO.readAllDetails();
		return userList;
	}

	public void inserContent(String uuid) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new FileReader("d:\\reader\\Customer.java"));
		bf.readLine();
		String line = "";
		String content = "";
		while ((line = bf.readLine()) != null) {
			content = content + "\n" + line;
		}
		System.out.println("READ Contents---" + content);
		qdexProDAO.inserContent(uuid, content);

	}

	public String readContent(String uuid) {
		String content = "";

		content = qdexProDAO.readContent(uuid);
		System.out.println("READ Contents---" + content);
		return content;
	}

	public void insertStepDetails(List<StepBean> allStepsList) {
		qdexProDAO.insertStepDetails(allStepsList);

	}

	public String readStepDetails(StepBean step) {
		// TODO Auto-generated method stub
		String id = qdexProDAO.readStepDetails(step);
		return id;
	}

	public void insertTearDownDetails(String tearDownId) {
		// TODO Auto-generated method stub
		qdexProDAO.insertTearDownDetails(tearDownId);
	}

	public void insertSetupDetails(String setupId) {
		// TODO Auto-generated method stub
		qdexProDAO.insertSetupDetails(setupId);
	}

	public String readTearDownId(String tearDownId) {
		// TODO Auto-generated method stub
		String id = qdexProDAO.readTearDownId(tearDownId);
		return id;
	}

	public String readSetupId(String setupId) {
		// TODO Auto-generated method stub
		String id = qdexProDAO.readSetupId(setupId);
		return id;
	}

	public void insertTestCaseDetails(String testCaseName, String steps, String tearDownResponseId,
			String setupResponseId) {
		qdexProDAO.insertTestCaseDetails(testCaseName, steps, tearDownResponseId, setupResponseId);

	}

	public void insertScenarioDetails(String scenarioName, String scenarioSetupId, String scenarioTearDownId,
			String testCaseId) {
		// TODO Auto-generated method stub
		qdexProDAO.insertScenarioDetails(scenarioName, scenarioSetupId, scenarioTearDownId, testCaseId);
	}

	public String readTestCaseId(String testCaseName) {
		String id = qdexProDAO.readTestCaseId(testCaseName);
		return id;
	}

	public ScenarioResponse readScenarioDetails(int id) {
		// TODO Auto-generated method stub
		ScenarioResponse response = qdexProDAO.readScenarioDetails(id);
		return response;
	}

	public SetupResponse readSetupDetails(String setupId) {
		// TODO Auto-generated method stub
		SetupResponse response = qdexProDAO.readSetupDetails(setupId);
		return response;
	}

	public StepDetails readStepCompleteDetails(String id) {
		// TODO Auto-generated method stub
		StepDetails response = qdexProDAO.readStepCompleteDetails(id);
		return response;
	}

	public TeardownResponse readTearDownDetails(String tearDownId) {
		// TODO Auto-generated method stub
		TeardownResponse response = qdexProDAO.readTearDownDetails(tearDownId);
		return response;
	}

	public TestCaseResponse readTestCaseDetails(String idVal) {
		// TODO Auto-generated method stub
		TestCaseResponse testCaseResponse = qdexProDAO.readTestCaseDetails(idVal);
		return testCaseResponse;
	}

	public String readScenarioId(String scenarioName) {
		String id = qdexProDAO.readScenarioId(scenarioName);
		return id;
	}

	public void createNewProject(String projName) {
		try {
			 List<String> list = new ArrayList<String>(); 
		        list.add("cmd.exe");
		        list.add("/c");
		        list.add("dir & mvn archetype:generate -DgroupId=com.qdex -DartifactId="+projName+" -DarchetypeArtifactId=maven-archetype-quickstart "
		        		+ "-DinteractiveMode=false & mvn deploy:deploy-file -Durl=D://MavenProjects//"+projName+" -DrepositoryId=some.id -Dfile=D://jars//mysql-connector-5.1.18.jar -DgroupId=com.mysql -DartifactId=mySql -Dversion=5.1.18 "
		        		+ "-Dpackaging=jar -DpomFile=D://MavenProjects//"+projName+"//pom.xml -DgeneratePom=true -DgeneratePom.description=\"My Project Description\"");
		        //list.add("mkdir "+projName); 
		  
		        // create the process 
		        ProcessBuilder build = new ProcessBuilder(); 
		        build.command(list);
		        build.directory(new File("D://MavenProjects"));

			Process pr1 = build.start(); 
			
			BufferedReader input = new BufferedReader(new InputStreamReader(pr1.getInputStream()));
			 
            String line=null;

            while((line=input.readLine()) != null) {
                System.out.println(line);
            }

            int exitVal = pr1.waitFor();
            System.out.println("Exited with error code "+exitVal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
