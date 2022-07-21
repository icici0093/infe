package com.infe.file;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FileStore{
	
	@Autowired
	private SqlSession sqlSession;

	public int insertFile(FileVO fileVO) {
		return sqlSession.insert("fileMapper.insertFile", fileVO);
	}

	public int deleteFile(FileVO fileVO) {
		return sqlSession.delete("fileMapper.deleteFile", fileVO);
	}

	public ArrayList<FileVO> selectList(FileVO fileVO) {
		return (ArrayList)sqlSession.selectList("fileMapper.selectList", fileVO);
	}

	public FileVO selectOne(int fiNo) {
		return sqlSession.selectOne("fileMapper.selectOne", fiNo);
	}

	public int deleteFileByFiNo(int fiNo) {
		return sqlSession.update("fileMapper.deleteFileByFiNo", fiNo);
	}

	public ArrayList<FileVO> printFile(FileVO fileVO) {
		return (ArrayList)sqlSession.selectList("fileMapper.printFile", fileVO);
	}

	public int deleteFileA(FileVO file) {
		return sqlSession.update("fileMapper.deleteFileByFiNo", file);
	}

}
