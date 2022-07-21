package com.infe.notice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.infe.common.PageInfo;
import com.infe.common.Pagination10;
import com.infe.common.RedirectWithMsg;
import com.infe.file.FileController;
import com.infe.file.FileVO;
import com.infe.file.FileService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService nService;
	
	@Autowired
	private FileService fService;
	
	// 전체 목록 조회
//	@RequestMapping(value="/notice/noticeList", method=RequestMethod.GET) 
//	public String noticeList() { 
//		return "notice/noticeListView"; 
//	}
	
	@RequestMapping(value="/notice/noticeList", method=RequestMethod.GET)
	public ModelAndView noticeList(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		Notice notice = new Notice();
		int currentPage = (page != null) ? page : 1;
		int listCount = nService.getListCount(notice);
		PageInfo pi = Pagination10.getPageInfo(currentPage, listCount);
		ArrayList<Notice> nList = nService.printAll(pi, notice);
		
		// 새 글 표시
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); // 2일동안 표시
		String nowDay = format.format(cal.getTime());
		
		if(!nList.isEmpty()) {
			mv.addObject("nList", nList);
			mv.addObject("pi", pi);
			mv.addObject("nowDay", nowDay);
			mv.setViewName("notice/noticeListView");
		}else {
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 조회
	@RequestMapping(value="/notice/noticeDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView noticeDetail(ModelAndView mv, @RequestParam("noNo") int noNo,
									@ModelAttribute FileVO fileVO) {
		// 조회수 증가
		nService.addReadCount(noNo);
		// 공지사항 상세 조회
		Notice notice = nService.printOne(noNo);
		// 파일 불러오기
		ArrayList<FileVO> noFiles = fService.printFile(fileVO);
		if(noFiles != null) {
			for(int i = 0; i < noFiles.size(); i++) { 
				notice.setNoFiles(noFiles);
			}
		}
		
		if(notice != null) {
			mv.addObject("notice", notice);
			mv.addObject("noFiles", noFiles);
			mv.setViewName("notice/noticeDetail");
		}else {
			mv.addObject("msg", "에러럴러러럴");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 검색
	@RequestMapping(value="/notice/noticeSearch", method=RequestMethod.GET)
	public String noticeSearch(@ModelAttribute Search search, Model model,
								@RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = nService.getPageCount(search);
		PageInfo pi = Pagination10.getPageInfo(currentPage, listCount);
		ArrayList<Notice> searchList = nService.printSearchAll(pi, search);
		
		// 새 글 표시x
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); // 2일동안 표시
		String nowDay = format.format(cal.getTime());
		
		if(!searchList.isEmpty()) {
			model.addAttribute("nList", searchList);
			model.addAttribute("pi", pi);
			model.addAttribute("nowDay", nowDay);
			model.addAttribute("search", search);
			return "notice/noticeListView";
		}else {
			return "common/errorPage";
		}
	}
	
	// 작성 뷰
	@RequestMapping(value="/notice/noticeWriteView", method=RequestMethod.GET)
	public String noticeWriteView() {
		return "notice/noticeWriteForm";
	}
	
	// 등록 동작
	@RequestMapping(value="/notice/noticeWrite", method=RequestMethod.POST)
	public String registerNotice(@ModelAttribute Notice notice, 
				@RequestParam(value="fList", required=false) List<MultipartFile> fList, 
				HttpServletRequest request) {
		// 실제 파일 저장
		ArrayList<FileVO> noFiles = null;
		if(fList != null && !fList.isEmpty()) {
			noFiles = new FileController().saveFile(fList, request);
		}
		
		// DB 저장
		int result = nService.registerNotice(notice);
		
		// 번호 가져오기
		ArrayList<Notice> recentNum = nService.readNoticeNum(notice);
		int num = recentNum.get(0).getNoNo();
		
		if(result > 0) {
			// file DB 저장
			int fiResult = 0;
			if(noFiles != null) {
				for(FileVO file : noFiles) {
					file.setBoNo(num);
					
					fiResult = fService.uploadFile(file);
				}
			}else {
				fiResult = 1;
			}
			
			if(fiResult > 0) { 
				return new RedirectWithMsg().redirect(request, "게시글이 등록되었습니다.", "/notice/noticeList");
			}else {
				return new RedirectWithMsg().redirect(request, "파일 등록 실패!", "/notice/noticeList");
			}
		}else {
			return new RedirectWithMsg().redirect(request, "게시글 등록 실패!", "/notice/noticeList");
		}
	}
	
	// 삭제
	@RequestMapping(value="/notice/noticeDelete", method=RequestMethod.GET)
	public String DeleteNotice(HttpServletRequest request, @RequestParam("noNo") int noNo) {
		
		// 파일 삭제
		int fiResult = 0;
		FileVO fileVO = new FileVO(noNo);
		ArrayList<FileVO> noFiles = fService.selectList(fileVO);
		if(!noFiles.isEmpty()) {
			String folder = "\\nUploadFiles";
			for(FileVO file : noFiles) {
				new FileController().deleteFile(folder, file.getFiSaveFileName(), request);
			}
			
			fiResult = fService.removeFile(fileVO);
		}else {
			fiResult = 1;
		}
		
		int noResult = 0;
		if(fiResult > 0) {
			noResult = nService.removeNotice(noNo);
			if(noResult > 0) {
				return new RedirectWithMsg().redirect(request, "게시글이 삭제되었습니다.", "noticeList");
			}else {
				return new RedirectWithMsg().redirect(request, "게시글 삭제 실패", "noticeList");
			}
		}else {
			return new RedirectWithMsg().redirect(request, "파일 삭제 실패", "noticeList");
		}
		
	}
	
	// 수정
	@RequestMapping(value="/notice/noticeModifyView")
	public ModelAndView noticeUpdateView(ModelAndView mv, @RequestParam("noNo") int noNo,
										@ModelAttribute FileVO fileVO) {
		Notice notice = nService.printOne(noNo);
		
		// 파일 불러오기
		ArrayList<FileVO> noFiles = fService.printFile(fileVO);
		if(noFiles != null) {
			for(int i = 0; i < noFiles.size(); i++) { 
				notice.setNoFiles(noFiles);
			}
		}
		
		if(notice != null) {
			mv.addObject("notice", notice).setViewName("notice/noticeModify");
		}else {
			mv.addObject("msg", "조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 수정 동작
	@RequestMapping(value="/notice/noticeModify", method=RequestMethod.POST)
	public ModelAndView noticeUpdate(HttpServletRequest request, ModelAndView mv, @ModelAttribute Notice notice,
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
		ArrayList<FileVO> noFiles = null;
		if(fList != null && !fList.isEmpty()) {
			noFiles = new FileController().saveFile(fList, request);
		}
		
		// DB 수정
		int nResult = nService.modifyNotice(notice);
		
		// 최근 변경된 날짜를 이용해 번호 가져오기..
		ArrayList<Notice> thisNum = nService.readUpdateDate(notice);
		int num = thisNum.get(0).getNoNo();
		
		if(nResult > 0) {
			int fResult = 0;
			if(noFiles != null) {
				for(FileVO file : noFiles) {
					file.setBoNo(num);
					
					fResult = fService.uploadFile(file);
				}
			}else {
				fResult = 1;
			}
			if(fResult > 0) {
				mv.addObject("notice", notice);
				mv.setViewName("redirect:/notice/noticeDetail?noNo=" + notice.getNoNo());
			}else {
				mv.addObject("msg", "파일 수정 오류").setViewName("common/errorPage");
			}
		}else {
			mv.addObject("msg", "게시글 수정 오류").setViewName("common/errorPage");
		}
		return mv;
	}
}
