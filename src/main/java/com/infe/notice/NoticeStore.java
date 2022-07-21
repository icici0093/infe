package com.infe.notice;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infe.common.PageInfo;

@Repository
public class NoticeStore{

	@Autowired
	public SqlSession sqlSession;
	
	public int selectListCount(Notice notice) {
		return sqlSession.selectOne("noticeMapper.selectListCount", notice);
	}

	public ArrayList<Notice> selectList(PageInfo pi, Notice notice) {
		// RowBounds는 쿼리문을 변경하지 않고도 페이징을 처리할 수있게 해주는 클래스
		// RowBounds의 동작은 offset값과 limit값을 이용해서 동작함
		// offset값은 변하는 값이고 limit값은 고정값
		// limit값이 한 페이지당 보여주고 싶은 게시물의 갯수이고
		// offset값은 건너뛰어야 할 값임
		// ex) currentPage가 1일 때 offset은 0, limit는 10이 되어 10개값을 보여줌
		//     currentPage가 2일 때 offset은 10이 되어서 10개를 건너뛰고 11개부터 20까지의 10개의 값을 보여줌

		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("noticeMapper.selectNoticeList", notice, rowBounds);
	}

	public int insertNotice(Notice notice) {
		return sqlSession.insert("noticeMapper.insertNotice", notice);
	}

	public int addReadCount(int noNo) {
		return sqlSession.update("noticeMapper.updateCount", noNo);
	}

	public Notice selectOne(int noNo) {
		return sqlSession.selectOne("noticeMapper.selectOne", noNo);
	}

	public int deleteNotice(int noNo) {
		return sqlSession.update("noticeMapper.deleteNotice", noNo);
	}

	public int selectPageCount(Search search) {
		return sqlSession.selectOne("noticeMapper.selectSearchCount", search);
	}

	public ArrayList<Notice> selectSearchList(PageInfo pi, Search search) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowbounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("noticeMapper.selectSearchList", search, rowbounds);
	}

	public ArrayList<Notice> readNoticeNum(Notice notice) {
		return (ArrayList)sqlSession.selectList("noticeMapper.readNoticeNum", notice);
	}

	public int updateNotice(Notice notice) {
		return sqlSession.update("noticeMapper.updateNotice", notice);
	}

	public ArrayList<Notice> readUpdateDate(Notice notice) {
		return (ArrayList)sqlSession.selectList("noticeMapper.readUpdateDate", notice);
	}

}
