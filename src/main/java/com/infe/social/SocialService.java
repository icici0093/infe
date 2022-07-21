package com.infe.social;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infe.common.CubeMap;
import com.infe.common.CubeUtils;
import com.infe.common.PageInfo;
import com.infe.common.Pagination10;
import com.infe.common.PagingUtil;
import com.infe.common.YoutubeAPI;
import com.infe.common.dao.BaseDao;

@Service
public class SocialService{

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	public SocialStore sStore;
	
	@Autowired
	public YoutubeAPI ytb;
	
	private final String THIS_VIEW = "/social/";
	
	private final String [] boardParams = {"searchCondition", "searchValue"};
	
	public String socialList(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		CubeMap param = new CubeMap(request);
		
		// 검색 조건 세팅
		CubeMap searchMap = new CubeMap();
		searchMap = param.copy();
		
		int pg = param.getInt("pg", 1);
		int pp = param.getInt("pp", 9);
		
		int startRow = PagingUtil.getStartRow(pg, pp);
		int endRow   = PagingUtil.getEndRow(pg, pp);
		
		searchMap.set("startRow", startRow);
		searchMap.set("endRow", endRow);
		
		param.set("pg", pg);
		param.set("pp", pp);
		
		List<CubeMap> movList = sStore.getMovList(searchMap);
		int movCount = sStore.getMovCount(searchMap);
		
//		List<CubeMap> replyList = sStore.getSocialList(searchMap);
//		int replyCount = sStore.getSocialCount(searchMap);
		
		// 새 글 표시
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, -1); // 2일동안 표시
//		String nowDay = format.format(cal.getTime());
		
		// 페이징 처리
		String paging = PagingUtil.Paging(param.getInt("pp", 9), param.getInt("pg", 1), movCount);
//		model.addAttribute("nowDay", nowDay);
		model.addAttribute("movList", movList);
		model.addAttribute("movCount", movCount);
		model.addAttribute("viewCount", CubeUtils.addComma(movCount+""));
//		model.addAttribute("replyList", replyList);
		model.addAttribute("paramdata", param);
		model.addAttribute("param_str", CubeUtils.setParamstr(boardParams, param));
		model.addAttribute("paging", paging);
		
		return THIS_VIEW + "socialList";
	}
	
	public Map<String, Object> setReply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CubeMap param = new CubeMap(request);
		Map<String, Object> rtnParam = new HashMap<String, Object>();
		
		String msg = "";
		
		boolean status = false;
		boolean result = false;

		ytb.getYoutube();
//		ytb.getYoutubeReply();
		
		if(result) {
			rtnParam.put("msg", msg);
			rtnParam.put("status", status);
		}
		
		return rtnParam;
	}
	
	public String movView(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes ) throws Exception {
		
		CubeMap param = new CubeMap(request);
		System.out.println("adssssssssssss");
		List<CubeMap> thisList = sStore.getMovInfo(param);
		
		model.addAttribute("thisList", thisList);
//		model.addAttribute("path", path);
		
		return THIS_VIEW + "mov_view";
	}

}
