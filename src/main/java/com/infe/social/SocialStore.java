package com.infe.social;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infe.common.CubeMap;
import com.infe.common.PageInfo;
import com.infe.common.dao.BaseDao;

@Repository
public class SocialStore{

	@Autowired
	private BaseDao baseDao;
	
	public List<CubeMap> getMovList(CubeMap param) throws Exception {
		List<CubeMap> thisList = baseDao._List("socialMapper.getMovList", param);
		return thisList;
	}
	
	public int getMovCount(CubeMap param) throws Exception {
		int count = (Integer) baseDao._scalar("socialMapper.getMovCount", param);
		return count;
	}
	
	public List<CubeMap> getSocialList(CubeMap param) throws Exception {
		List<CubeMap> thisList = baseDao._List("socialMapper.getReplyList", param);
		return thisList;
	}
	
	public int getSocialCount(CubeMap param) throws Exception {
		int count = (Integer) baseDao._scalar("socialMapper.getReplyCount", param);
		return count;
	}

	public boolean setYoutubeMov(CubeMap param) throws Exception {
		boolean result = baseDao._Insert("socialMapper.setYoutubeMov", param);
		return result;
	}
	
	public boolean setYoutubeReply(CubeMap param) throws Exception {
		boolean result = baseDao._Insert("socialMapper.setYoutubeReply", param);
		return result;
	}
	
	public List<CubeMap> getMovInfo(CubeMap param) throws Exception {
		List<CubeMap> thisList = baseDao._List("socialMapper.getMovInfo", param);
		return thisList;
	}
}
