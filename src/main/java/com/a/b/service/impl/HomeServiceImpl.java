package com.a.b.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a.b.cmmn.dao.CmmnDao;
import com.a.b.cmmn.utils.HwyMap;
import com.a.b.service.HomeService;

@Service("homeService")
public class HomeServiceImpl implements HomeService {
	
	@Autowired
	private CmmnDao cmmnDao;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String getDbTest(HwyMap hwyMap) throws Exception {
		return cmmnDao.selectByPk("asg.dtdv1002.getDbTest", hwyMap);
	}

	@Override
	public void insertDbTest(HwyMap hwyMap) throws Exception {
		cmmnDao.insert("asg.dtdv1002.insertDbTest", hwyMap);
	}

}
