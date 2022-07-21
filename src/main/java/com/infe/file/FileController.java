package com.infe.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	@Autowired
	private FileService fService;
	
	// 파일 다운로드
	@RequestMapping(value="/file/download", method=RequestMethod.GET)
	public void fileDownload(HttpServletRequest request, HttpServletResponse response,
							@RequestParam("fiNo") int fiNo) throws Exception{
		FileVO fileVO = fService.selectOne(fiNo);
		
		String folderName =  "\\nUploadFiles\\";
		String filePath = request.getSession().getServletContext().getRealPath("resources") + folderName + fileVO.getFiSaveFileName();
		
		File file = new File(filePath);
		
		if(file.isFile()) {
			String userAgent = request.getHeader("User-Agent");
			
			String fileNameOrg = null;
			boolean ie = userAgent.indexOf("MSIE") > -1;
			if(ie) {
				fileNameOrg = URLEncoder.encode(fileVO.getFiRealFileName(), "UTF-8").replaceAll("\\", "%20");
				response.setHeader("Content-Disposition", "attachment; filename=" + fileNameOrg + ";");
			}else {
				fileNameOrg = new String(fileVO.getFiRealFileName().getBytes("UTF-8"), "ISO-8859-1");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileNameOrg + "\"");
			}
			
			response.setContentType("application/octet-stream");
			response.setContentLength((int)file.length());
			response.setHeader("Content-Trensfer-Encoding", "binary;");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");
			
			FileInputStream fileIn = new FileInputStream(file);
			OutputStream output = response.getOutputStream();
			
			byte [] outputByte = new byte[4096];
			while(fileIn.read(outputByte, 0, 4096) != -1) {
				// 읽은 것을 다운로드 되도록 함
				output.write(outputByte, 0, 4096);
			}
			
			fileIn.close();
			output.flush();
			output.close();
		}
	}
	
	// 다중 파일 저장
	public ArrayList<FileVO> saveFile(List<MultipartFile> fList, HttpServletRequest request){
		
		// 저장 폴더 선택
		String folderName = "\\nUploadFiles";
		String savePath = request.getSession().getServletContext().getRealPath("resources") + folderName;
		File folder = new File(savePath);
		
		// 폴더 생성
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		// 정보 저장해서 넘겨줄 파일 생성
		ArrayList<FileVO> files = new ArrayList<FileVO>();
		int i = 1;
		for(MultipartFile mf : fList) {
			String fiRealName = mf.getOriginalFilename();
			
			// 파일명 변경
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
			String fiStoredName = sdf.format(new Date(System.currentTimeMillis())) + i++ + "." + fiRealName.substring(fiRealName.lastIndexOf(".") + 1);
			
			// 파일 저장
			String filePath = folder + "\\" + fiStoredName;
			
			try {
				mf.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// DB 저장을 위해 정보 넘기기
			FileVO fileVO = new FileVO(fiRealName, fiStoredName, filePath);
			files.add(fileVO);
		}
		return files;
	}
	
	// 파일 삭제
	public void deleteFile(String folder, String fileName, HttpServletRequest request) {
		// 실제 파일 경로를 만들어서 실제 파일 삭제
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + folder;
		File file = new File(savePath + "\\" + fileName);
		if(file.exists()) {
			file.delete();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/file/fileDelete")
	public String removeFile(@ModelAttribute FileVO file) {
		int result = fService.removeFileA(file);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
}
