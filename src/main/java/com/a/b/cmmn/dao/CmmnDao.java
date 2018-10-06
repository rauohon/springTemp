package com.a.b.cmmn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.a.b.cmmn.utils.HwyMap;

@Repository
public class CmmnDao extends SqlSessionDaoSupport {
	
	@Resource(name = "sqlSession")
	public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
		super.setSqlSessionFactory(sqlSession);
	}
	
	public int insert(String queryId, Object hwyMap) {
		return getSqlSession().insert(queryId, hwyMap);
	}
	
	public int update(String queryId, Object hwyMap) {
		return getSqlSession().update(queryId, hwyMap);
	}

	public int delete(String queryId, Object hwyMap) {
		return getSqlSession().delete(queryId, hwyMap);
	}
	
	public String selectByPk(String queryId, Object hwyMap) {
		return getSqlSession().selectOne(queryId, hwyMap);
	}
	
	@SuppressWarnings("rawtypes")
	public List<HwyMap> select(String queryId, Object hwyMap) {
		return getSqlSession().selectList(queryId, hwyMap);
	}
	
	
	
}
