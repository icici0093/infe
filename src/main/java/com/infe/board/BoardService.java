package com.infe.board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
import com.infe.common.dao.BaseDao;

@Service
public class BoardService{

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	public BoardStore bStore;
	
	private final String THIS_VIEW = "/board/";
	
	private final String [] boardParams = {"searchCondition", "searchValue"};
	
	public String boardList(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		CubeMap param = new CubeMap(request);
		
		// 검색 조건 세팅
		CubeMap searchMap = new CubeMap();
		searchMap = param.copy();
		
		int pg = param.getInt("pg", 1);
		int pp = param.getInt("pp", 10);
		
		int startRow = PagingUtil.getStartRow(pg, pp);
		int endRow   = PagingUtil.getEndRow(pg, pp);
		
		searchMap.set("startRow", startRow);
		searchMap.set("endRow", endRow);
		
		param.set("pg", pg);
		param.set("pp", pp);
		
		List<CubeMap> thisList = bStore.getBoardList(searchMap);
		int totalCount = bStore.getBoardCount(searchMap);
		
		// 새 글 표시
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, -1); // 2일동안 표시
//		String nowDay = format.format(cal.getTime());
		
		// 페이징 처리
		String paging = PagingUtil.Paging(param.getInt("pp", 10), param.getInt("pg", 1), totalCount);
		
//		model.addAttribute("nowDay", nowDay);
		model.addAttribute("thisList", thisList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("viewCount", CubeUtils.addComma(totalCount+""));
		model.addAttribute("paramdata", param);
		model.addAttribute("param_str", CubeUtils.setParamstr(boardParams, param));
		model.addAttribute("paging", paging);
		
		return THIS_VIEW + "boardList";
	}
	
	public String boardDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		CubeMap param = new CubeMap(request);
		
		String param_str = CubeUtils.setParamstr(boardParams, param);
		
		CubeMap thisEtt = bStore.boardDetail(param.get("boNo"));
		
		// 게시판 조회 결과가 없는 경우
		if ( !baseDao.chkObj(thisEtt) ) {
			redirectAttributes.addFlashAttribute("msg", "잘못된 접근입니다.");
			return "redirect:/board/boardList?" + param_str + "&pg=" + param.getInt("pg", 1);
		}
		
		List<CubeMap> fileArr = bStore.getArrayFileList(param);
		
		model.addAttribute("param_str", param_str);
		model.addAttribute("paramdata", param);
		model.addAttribute("thisEtt", thisEtt);
		model.addAttribute("fileArr", fileArr);
		
		return THIS_VIEW + "boardDetail";
	}
	
	public void getReplyList(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		CubeMap param = new CubeMap(request);
		
		String boNo = param.get("boNo");
		Integer page = Integer.parseInt(param.get("page"));
		
		int listCount = bStore.getReplyCount(param);
		
		int currentPage = (page != null) ? page : 1;
		PageInfo pi = Pagination10.getPageInfo(currentPage, listCount);
//		List<CubeMap> rList = bStore.getReplyList(pi, boNo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("page", pi);
//		map.put("rList", rList);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
//		gson.toJson(map, response.getWriter());
	}
	public int getListCount(Board board) {
		return bStore.selectListCount(board);
	}

	public ArrayList<Board> printAll(PageInfo pi, Board board) {
		return bStore.selectList(pi, board);
	}

	public int registerBoard(Board board) {
		return bStore.insertBoard(board);
	}

	public int addReadCount(int boNo) {
		return bStore.addReadCount(boNo);
	}

	public Board printOne(int boNo) {
		return bStore.selectOne(boNo);
	}

	public int removeBoard(int boNo) {
		return bStore.deleteBoard(boNo);
	}

	public int getPageCount(Search search) {
		return bStore.selectPageCount(search);
	}

	public ArrayList<Board> printSearchAll(PageInfo pi, Search search) {
		return bStore.selectSearchList(pi, search);
	}

	public ArrayList<Board> readBoardNum(Board board) {
		return bStore.readBoardNum(board);
	}

	public int modifyBoard(Board board) {
		return bStore.updateBoard(board);
	}

	public ArrayList<Board> readUpdateDate(Board board) {
		return bStore.readUpdateDate(board);
	}

	public int getReplyCount(Board board) {
		return bStore.selectReplyCount(board);
	}
	public int getReplyCount(Reply reply) {
		return bStore.selectReplyCount(reply);
	}
	
	public ArrayList<Board> printAllReply(PageInfo pi, int boNo) {
		return bStore.selectAllReply(pi, boNo);
	}

	public Board printOneReply(int boMotherNo) {
		return bStore.printOneReply(boMotherNo);
	}

	public int registerReply(Reply reply) {
		return bStore.insertReply(reply);
	}

	public int modifyReply(Reply reply) {
		return bStore.updateReply(reply);
	}

	public int deleteReply(int reNo) {
		return bStore.deleteReply(reNo);
	}

	public ArrayList<Board> printReComments(int boMotherNo) {
		return bStore.selectReComments(boMotherNo);
	}

	public int registerReComment(Reply reply) {
		return bStore.insertReCommnet(reply);
	}

}
