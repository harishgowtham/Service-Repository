package com.qdexpro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qdexpro.QdexProApplication;
import com.qdexpro.bean.InputRequestBean;
import com.qdexpro.service.QdexProService;

@RestController
public class QdexProController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QdexProController.class);
	
	 @Autowired
	    private QdexProService qdexProService;
	
	@RequestMapping(method = RequestMethod.POST, value="/register/employee")
	@ResponseBody
	public void registerStudent(@RequestBody InputRequestBean request) {
		LOGGER.info("Into Controller---->");
		qdexProService.insertData(request.getId(),request.getName(),request.getAge());
	}
}
