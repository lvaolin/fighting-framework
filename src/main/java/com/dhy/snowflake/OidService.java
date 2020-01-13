package com.dhy.snowflake;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



public class OidService implements IOidService {

	@Autowired
	private ZKSnowflakeIDGenerator idGenerator;

	//@Override
	//public Long generateObjectID() {
	//   return IDGenerator.generateObjectID();
	//}

	@Override
	public Long generateObjectID() {
		return idGenerator.getId();

	}
	@Override
	public List<Long> generateObjectID(int size) {
		List<Long> objectIDlist = new ArrayList<Long>();
		for (int i = 0; i < size; i++) {
			objectIDlist.add(idGenerator.getId());
		}
		return  objectIDlist;
	}
}
