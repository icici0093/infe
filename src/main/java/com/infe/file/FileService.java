package com.infe.file;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService{

	@Autowired
	public FileStore fStore;
	
	public int uploadFile(FileVO fileVO) {
		return fStore.insertFile(fileVO);
	}

	public int removeFile(FileVO fileVO) {
		return fStore.deleteFile(fileVO);
	}

	public ArrayList<FileVO> selectList(FileVO fileVO) {
		return fStore.selectList(fileVO);
	}

	public FileVO selectOne(int fiNo) {
		return fStore.selectOne(fiNo);
	}

	public int removeFileByFiNo(int fiNo) {
		return fStore.deleteFileByFiNo(fiNo);
	}

	public ArrayList<FileVO> printFile(FileVO fileVO) {
		return fStore.printFile(fileVO);
	}

	public int removeFileA(FileVO file) {
		return fStore.deleteFileA(file);
	}

}
