package com.infe.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.infe.common.CubeMap;

import org.mybatis.spring.SqlSessionTemplate;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

@Repository
public class BaseDao{
	
	public final Logger logger = LoggerFactory.getLogger(BaseDao.class);
	
	@Resource(name="SqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	// 목록 불러오기
	public List<CubeMap> _List(String targetNamespace, CubeMap InEntity){
		List<CubeMap> result = null;
		
		try {
			result = sqlSession.selectList(targetNamespace, InEntity);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
		}
		
		return result;
	}
	
	// 목록 불러오기
	public List<HashMap<String, Object>> _HashList(String targetNamespace, CubeMap InEntity){
		List<HashMap<String, Object>> result = null;
		
		try {
			result = sqlSession.selectList(targetNamespace, InEntity);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
		}
		
		return result;
	}
	
	// 목록 불러오기
	public List<String> _StringList(String targetNamespace, CubeMap InEntity){
		List<String> result = null;
		
		try {
			result = sqlSession.selectList(targetNamespace, InEntity);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
		}
		
		return result;
	}
	
	// 목록 불러오기
	public CubeMap _Row(String targetNamespace, CubeMap InEntity){
		CubeMap result = null;
		
		try {
			result = (CubeMap) sqlSession.selectOne(targetNamespace, InEntity);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
		}
		
		return result;
	}
	
	// 목록 불러오기
	public CubeMap _Row(String targetNamespace, String defcode){
		CubeMap result = null;
		
		try {
			result = (CubeMap) sqlSession.selectOne(targetNamespace, defcode);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
		}
		
		return result;
	}
	
	public boolean _Insert(String targetNamespace, CubeMap InEntity) {
		boolean result = false;
		
		try {
			int result_int = sqlSession.insert(targetNamespace, InEntity);
			
			if(result_int > 0) 	result = true;
			else 				result = false;
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
			result = false;
		}
		
		return result;
	}
	
	public boolean _InsertEach(String targetNamespace, List<CubeMap> thisList) {
		boolean result = false;
		
		try {
			HashMap this_map = new HashMap();
			this_map.put("thisList", thisList);
			int result_int = sqlSession.insert(targetNamespace, this_map);
			
			if(result_int > 0) 	result = true;
			else 				result = false;
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
			result = false;
		}
		
		return result;
	}
	
	public boolean _Update(String targetNamespace, CubeMap InEntity) {
		boolean result = false;
		
		try {
			int result_int = sqlSession.update(targetNamespace, InEntity);
			
			if(result_int > 0) 	result = true;
			else 				result = false;
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
			result = false;
		}
		
		return result;
	}
	
	public boolean _Delete(String targetNamespace, CubeMap InEntity) {
		boolean result = false;
		
		try {
			int result_int = sqlSession.delete(targetNamespace, InEntity);
			
			if(result_int > 0) 	result = true;
			else 				result = false;
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
			result = false;
		}
		
		return result;
	}
	
	public boolean _Delete(String targetNamespace, String delstr) {
		boolean result = false;
		
		try {
			int result_int = sqlSession.delete(targetNamespace, delstr);
			
			if(result_int > 0) 	result = true;
			else 				result = false;
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
			result = false;
		}
		
		return result;
	}
	
	public Object _scalar(String targetNamespace, CubeMap InEntity) {
		Object scalar = null;
		
		try {
			scalar = sqlSession.selectOne(targetNamespace, InEntity);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
		}
		
		return scalar;
	}
	
	public Object _scalar(String targetNamespace, String InEntity) {
		Object scalar = null;
		
		try {
			scalar = sqlSession.selectOne(targetNamespace, InEntity);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(targetNamespace + " > " + e.getMessage());
		}
		
		return scalar;
	}
	
	public void commit() {
		sqlSession.commit();
	}
	
	public void rollback() {
		sqlSession.rollback();
	}
	
	public void close() {
		sqlSession.close();
	}
	
	public boolean chkList(List<CubeMap> obj) {
		boolean chkbool = true;
		if (obj == null) return false;
		if (obj.size() < 1) return false;
		return chkbool;
	}
	
	public boolean chkObj(CubeMap obj) {
		boolean chkbool = true;
		if (obj == null) return false;
		if (obj.isEmpty()) return false;
		return chkbool;
	}
}