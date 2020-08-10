package com.qdexpro.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.qdexpro.bean.InputIndexDetails;
import com.qdexpro.bean.InputJSONDoc;

public class IndexSearchService {

	@SuppressWarnings("resource")
	public void insertJSONDocument(InputIndexDetails indexDetails) {
		// TODO Auto-generated method stub
		Settings settings = Settings.builder().put("cluster.name", "qdexCluster").build();
		try {
			TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new 
					InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
			System.out.println("Transport Client created");
			client.prepareIndex(indexDetails.getIndexName(), indexDetails.getIndexType(), indexDetails.getIndexId())
					.setSource(putJsonDocument(indexDetails.getInputJSONDoc())).execute().actionGet();
			System.out.println("Index created");
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static Map<String, Object> putJsonDocument(InputJSONDoc jsonDoc) {
		Map<String, Object> jsonDocument = new HashMap<String, Object>();
		jsonDocument.put("title", jsonDoc.getTitle());
		jsonDocument.put("conten", jsonDoc.getContent());
		jsonDocument.put("postDate", jsonDoc.getDate());
		jsonDocument.put("tags", jsonDoc.getTags());
		return jsonDocument;
	}

}
