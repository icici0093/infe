package com.infe.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infe.common.PageInfo;
import com.infe.common.Pagination10;
import com.infe.common.RedirectWithMsg;
import com.infe.file.FileController;
import com.infe.file.FileVO;
import com.infe.file.FileService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	@Autowired
	private FileService fService;

	@RequestMapping(value="/board/boardList", method=RequestMethod.GET)
	public String boardList(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		return bService.boardList(request, response, model, redirectAttributes);
	}
//	@RequestMapping(value="/board/boardList2", method=RequestMethod.GET)
//	public ModelAndView boardList(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
//		Board board = new Board();
//		int currentPage = (page != null) ? page : 1;
//		int listCount = bService.getListCount(board);
//		PageInfo pi = Pagination10.getPageInfo(currentPage, listCount);
//		ArrayList<Board> bList = bService.printAll(pi, board);
//		
//		// 새 글 표시
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, -1); // 2일동안 표시
//		String nowDay = format.format(cal.getTime());
//		
//		if(!bList.isEmpty()) {
//			mv.addObject("bList", bList);
//			mv.addObject("pi", pi);
//			mv.addObject("nowDay", nowDay);
//			mv.setViewName("board/boardList");
//		}else {
//			mv.setViewName("board/boardList");
//		}
//		return mv;
//	}
	
	@RequestMapping(value="/board/boardDetail", method=RequestMethod.GET)
	public String boardDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		return bService.boardDetail(request, response, model, redirectAttributes);
	}
	// 조회
	@RequestMapping(value="/board/boardDetail2", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView boardDetail(ModelAndView mv, @RequestParam("boNo") int boNo,
									@ModelAttribute FileVO fileVO, HttpServletRequest request,HttpServletResponse response) {
		
		Cookie[] cookies = request.getCookies();
		
		// 비교하기위한 새로운 쿠키
		Cookie viewCookie = null;
				
		// 쿠키가 있을 경우
		if(cookies != null && cookies.length > 0) {
			for(int i = 0; i < cookies.length; i++) {
				// Cookie의 name이 cookie + boNo와 일치하는 쿠키를 viewCookie에 넣어줌
				if(cookies[i].getName().equals("cookie"+boNo)) {
					System.out.println("조회수 안올라감~~~");
					viewCookie = cookies[i];
				}
			}
		}
		
		if(viewCookie == null) {
			Cookie newCookie = new Cookie("cookie"+boNo, "|" + boNo + "|");
			
			response.addCookie(newCookie);
			// 조회수 증가
			int result = bService.addReadCount(boNo);
			if(result > 0) {
				System.out.println("조회수 증가");
			}else {
				System.out.println("조회수 증가 오류");
			} 
		}
		// 공지사항 상세 조회
		Board board = bService.printOne(boNo);
		
		// 파일 불러오기
		ArrayList<FileVO> files = fService.printFile(fileVO);
		if(files != null) {
			for(int i = 0; i < files.size(); i++) { 
				board.setFiles(files);
			}
		}
		
		if(board != null) {
			mv.addObject("board", board);
			mv.addObject("files", files);
			mv.setViewName("board/boardDetail");
		}else {
			mv.addObject("msg", "에러럴러러럴");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 검색
	@RequestMapping(value="/board/boardSearch", method=RequestMethod.GET)
	public String boardSearch(@ModelAttribute Search search, Model model,
								@RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = bService.getPageCount(search);
		PageInfo pi = Pagination10.getPageInfo(currentPage, listCount);
		ArrayList<Board> searchList = bService.printSearchAll(pi, search);
		
		// 새 글 표시x
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); // 2일동안 표시
		String nowDay = format.format(cal.getTime());
		
		if(!searchList.isEmpty()) {
			model.addAttribute("bList", searchList);
			model.addAttribute("pi", pi);
			model.addAttribute("nowDay", nowDay);
			model.addAttribute("search", search);
			return "board/boardList";
		}else {
			return "board/boardList";
		}
	}
	
	// 작성 뷰
	@RequestMapping(value="/board/boardWriteView", method=RequestMethod.GET)
	public String BoardWriteView() {
		return "board/boardWrite";
	}
	
	// 등록 동작
	@RequestMapping(value="/board/boardWrite", method=RequestMethod.POST)
	public String registerBoard(@ModelAttribute Board board, 
				@RequestParam(value="fList", required=false) List<MultipartFile> fList, 
				HttpServletRequest request) {
		// 실제 파일 저장
		ArrayList<FileVO> files = null;
		if(fList != null && !fList.isEmpty()) {
			files = new FileController().saveFile(fList, request);
		}
		
		// DB 저장
		int result = bService.registerBoard(board);
		
		// 번호 가져오기
		ArrayList<Board> recentNum = bService.readBoardNum(board);
		int num = recentNum.get(0).getBoNo();
		
		if(result > 0) {
			// file DB 저장
			int fiResult = 0;
			if(files != null) {
				for(FileVO file : files) {
					file.setBoNo(num);
					
					fiResult = fService.uploadFile(file);
				}
			}else {
				fiResult = 1;
			}
			
			if(fiResult > 0) { 
				return new RedirectWithMsg().redirect(request, "게시글이 등록되었습니다.", "/board/boardList");
			}else {
				return new RedirectWithMsg().redirect(request, "파일 등록 실패!", "/board/boardList");
			}
		}else {
			return new RedirectWithMsg().redirect(request, "게시글 등록 실패!", "/board/boardList");
		}
	}
	
	// 삭제
	@RequestMapping(value="/board/boardDelete", method=RequestMethod.GET)
	public String DeleteBoard(HttpServletRequest request, @RequestParam("boNo") int boNo) {
		
		// 파일 삭제
		int fiResult = 0;
		FileVO fileVO = new FileVO(boNo);
		ArrayList<FileVO> files = fService.selectList(fileVO);
		if(!files.isEmpty()) {
			String folder = "\\uploadFiles";
			for(FileVO file : files) {
				new FileController().deleteFile(folder, file.getFiSaveFileName(), request);
			}
			
			fiResult = fService.removeFile(fileVO);
		}else {
			fiResult = 1;
		}
		
		int noResult = 0;
		if(fiResult > 0) {
			noResult = bService.removeBoard(boNo);
			if(noResult > 0) {
				return new RedirectWithMsg().redirect(request, "게시글이 삭제되었습니다.", "boardList");
			}else {
				return new RedirectWithMsg().redirect(request, "게시글 삭제 실패", "boardList");
			}
		}else {
			return new RedirectWithMsg().redirect(request, "파일 삭제 실패", "boardList");
		}
		
	}
	
	// 수정
	@RequestMapping(value="/board/boardModifyView")
	public ModelAndView BoardUpdateView(ModelAndView mv, @RequestParam("boNo") int boNo,
										@ModelAttribute FileVO fileVO) {
		Board board = bService.printOne(boNo);
		
		// 파일 불러오기
		ArrayList<FileVO> files = fService.printFile(fileVO);
		if(files != null) {
			for(int i = 0; i < files.size(); i++) { 
				board.setFiles(files);
			}
		}
		
		if(board != null) {
			mv.addObject("board", board).setViewName("board/boardModify");
		}else {
			mv.addObject("msg", "조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 수정 동작
	@RequestMapping(value="/board/boardModify", method=RequestMethod.POST)
	public ModelAndView BoardUpdate(HttpServletRequest request, ModelAndView mv, @ModelAttribute Board board,
									@RequestParam(value="fList", required=false) List<MultipartFile> fList,
									@RequestParam(value="delFiles", required=false) List<String> delFiles) {
		
		// 기존 파일 삭제
		if(delFiles != null && !delFiles.isEmpty()) {
			for(int i = 0; i < delFiles.size(); i++) {
				FileVO file = fService.selectOne(Integer.parseInt(delFiles.get(i)));
				new FileController().deleteFile("\\nUploadFiled", file.getFiSaveFileName(), request);
				fService.removeFileByFiNo(file.getFiNo());
			}
		}
		
		// 새 파일 업로드
		ArrayList<FileVO> files = null;
		if(fList != null && !fList.isEmpty()) {
			files = new FileController().saveFile(fList, request);
		}
		
		// DB 수정
		int nResult = bService.modifyBoard(board);
		
		// 최근 변경된 날짜를 이용해 번호 가져오기..
		ArrayList<Board> thisNum = bService.readUpdateDate(board);
		int num = thisNum.get(0).getBoNo();
		
		if(nResult > 0) {
			int fResult = 0;
			if(files != null) {
				for(FileVO file : files) {
					file.setBoNo(num);
					
					fResult = fService.uploadFile(file);
				}
			}else {
				fResult = 1;
			}
			if(fResult > 0) {
				mv.addObject("board", board);
				mv.setViewName("redirect:/board/boardDetail?boNo=" + board.getBoNo());
			}else {
				mv.addObject("msg", "파일 수정 오류").setViewName("common/errorPage");
			}
		}else {
			mv.addObject("msg", "게시글 수정 오류").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// ============== 댓글 ================
	@RequestMapping(value="/board/replyList1", method=RequestMethod.GET)
	public void getReplyList(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		bService.getReplyList(request, response, model, redirectAttributes);
	}
	// 댓글 리스트
	@RequestMapping(value="/board/replyList", method=RequestMethod.GET)
	public void getReplyList(HttpServletResponse response, @RequestParam("boNo") int boNo,
			@RequestParam(value="page", required=false) Integer page) throws IOException{
		Reply reply = new Reply();
		reply.setBoNo(boNo);
		
		System.out.println("boNo === " + boNo);
		int listCount = bService.getReplyCount(reply);
		
		int currentPage = (page != null) ? page : 1;
		PageInfo pi = Pagination10.getPageInfo(currentPage, listCount);
		ArrayList<Board> rList = bService.printAllReply(pi, boNo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("page", pi);
		map.put("rList", rList);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
		gson.toJson(map, response.getWriter());
	}
//	// 답글 리스트
//	@RequestMapping(value="/board/reComment", method=RequestMethod.GET)
//	public void getReComment(HttpServletResponse response, @RequestParam("boMotherNo") int boMotherNo,
//							@RequestParam(value="page", required=false) Integer page) throws IOException{
//		Board board = new Board();
//		board.setBoMotherNo(boMotherNo);
//		
//		ArrayList<Board> cList = bService.printReComments(boMotherNo);
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("cList", cList);
//		
//		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
//		gson.toJson(map, response.getWriter());
//	}
	// 댓글 하나
	@RequestMapping(value="/board/replyOne", method=RequestMethod.GET)
	public void getReplyOne(HttpServletResponse response, @RequestParam("boMotherNo") int boMotherNo) throws IOException {
		// 댓글 하나
		Board bOne = bService.printOneReply(boMotherNo);
		// 댓글 총 개수
		Board board = new Board();
		board.setBoMotherNo(boMotherNo);
		int count = bService.getReplyCount(board);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bOne", bOne);
		map.put("count", count);
		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
		gson.toJson(map, response.getWriter());
		
	}
	// 등록
	@ResponseBody
	@RequestMapping(value="/board/addReply", method=RequestMethod.POST)
	public String replyRegister(@ModelAttribute Reply reply) {
		int result = bService.registerReply(reply);
		
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	// 대댓글 등록 
	@ResponseBody
	@RequestMapping(value="/board/addReComment", method=RequestMethod.POST)
	public String reReplyRegister(@ModelAttribute Reply reply) {
		int result = bService.registerReComment(reply);
		
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	// 수정
	@ResponseBody
	@RequestMapping(value="/board/modifyReply", method=RequestMethod.POST)
	public String replyModify(@ModelAttribute Reply reply) {
		int result = bService.modifyReply(reply);
		
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	// 삭제
	@ResponseBody
	@RequestMapping(value="/board/deleteReply", method=RequestMethod.GET)
	public String replyRemove(@RequestParam("reNo") int reNo) {
		int result = bService.deleteReply(reNo);
		
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
}
