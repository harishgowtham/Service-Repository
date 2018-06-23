package com.qdexpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdexpro.dao.QdexProDAO;

@Service
public class QdexProService {

	 @Autowired
	    private QdexProDAO qdexProDAO;
	 
	public void insertData(String id, String name, String age){
		qdexProDAO.insertData(id,name,age);
	}
}
