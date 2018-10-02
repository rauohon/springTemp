package com.a.b.service.impl;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a.b.service.HomeService;
import com.a.b.service.impl.dao.HomeMapper;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeMapper homeMapper;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String getDbTest() throws Exception {
		return homeMapper.getDbTest();
	}

	@Override
	public void insertDbTest() throws Exception {
		homeMapper.insertDbTest();
	}

}
