package com.a.b.service.impl;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a.b.service.HomeService;
import com.a.b.service.impl.dao.HomeMapper;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeMapper homeMapper;
	
	@Override
	public String getDbTest() throws Exception {
		return homeMapper.getDbTest();
	}

	@Override
	public void insertDbTest() throws Exception {
		homeMapper.insertDbTest();
	}

}
