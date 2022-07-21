package com.infe.board;

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
public class BoardStore{

	@Autowired
	public SqlSession sqlSession;
	
	@Autowired
	private BaseDao baseDao;
	
	public List<CubeMap> getBoardList(CubeMap param) throws Exception {
		List<CubeMap> thisList = baseDao._List("boardMapper.getBoardList", param);
		return thisList;
	}
	
	public int getBoardCount(CubeMap param) throws Exception {
		int count = (Integer) baseDao._scalar("boardMapper.getBoardCount", param);
		return count;
	}

	public CubeMap boardDetail(String boNo) throws Exception {
		CubeMap thisEtt = baseDao._Row("boardMapper.getBoardDetail", boNo);
		return thisEtt;
	}
	
	public List<CubeMap> getArrayFileList(CubeMap param) throws Exception {
		List<CubeMap> thisEtt = baseDao._List("boardMapper.getArrayFileList", param);
		return thisEtt;
	}

	public int getReplyCount(CubeMap param) {
		int count = (Integer) baseDao._scalar("boardMapper.getReplyCount", param);
		return count;
	}

	public List<CubeMap> getReplyList(PageInfo pi, String boNo) {
//		List<CubeMap>
		return null;
	}

	public int selectListCount(Board board) {
		return sqlSession.selectOne("boardMapper.selectListCount", board);
	}

	public ArrayList<Board> selectList(PageInfo pi, Board board) {
		// RowBounds는 쿼리문을 변경하지 않고도 페이징을 처리할 수있게 해주는 클래스
		// RowBounds의 동작은 offset값과 limit값을 이용해서 동작함
		// offset값은 변하는 값이고 limit값은 고정값
		// limit값이 한 페이지당 보여주고 싶은 게시물의 갯수이고
		// offset값은 건너뛰어야 할 값임
		// ex) currentPage가 1일 때 offset은 0, limit는 10이 되어 10개값을 보여줌
		//     currentPage가 2일 때 offset은 10이 되어서 10개를 건너뛰고 11개부터 20까지의 10개의 값을 보여줌

		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("boardMapper.selectBoardList", board, rowBounds);
	}

	public int insertBoard(Board board) {
		return sqlSession.insert("boardMapper.insertBoard", board);
	}

	public int addReadCount(int boNo) {
		return sqlSession.update("boardMapper.updateCount", boNo);
	}

	public Board selectOne(int boNo) {
		return sqlSession.selectOne("boardMapper.selectOne", boNo);
	}

	public int deleteBoard(int boNo) {
		return sqlSession.update("boardMapper.deleteBoard", boNo);
	}

	public int selectPageCount(Search search) {
		return sqlSession.selectOne("boardMapper.selectSearchCount", search);
	}

	public ArrayList<Board> selectSearchList(PageInfo pi, Search search) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowbounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList", search, rowbounds);
	}

	public ArrayList<Board> readBoardNum(Board board) {
		return (ArrayList)sqlSession.selectList("boardMapper.readBoardNum", board);
	}

	public int updateBoard(Board board) {
		return sqlSession.update("boardMapper.updateBoard", board);
	}

	public ArrayList<Board> readUpdateDate(Board board) {
		return (ArrayList)sqlSession.selectList("boardMapper.readUpdateDate", board);
	}

	public int selectReplyCount(Board board) {
		return sqlSession.selectOne("boardMapper.selectReplyCount", board);
	}
	public int selectReplyCount(Reply reply) {
		return sqlSession.selectOne("replyMapper.selectReplyCount", reply);
	}

	public ArrayList<Board> selectAllReply(PageInfo pi, int boNo) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
//		return (ArrayList)sqlSession.selectList("boardMapper.selectAllReply", boMotherNo, rowBounds);
		return (ArrayList)sqlSession.selectList("replyMapper.selectAllReply", boNo, rowBounds);
	}

	public Board printOneReply(int boMotherNo) {
		return sqlSession.selectOne("boardMapper.selectOneReply", boMotherNo);
	}

	public int insertReply(Reply reply) {
		return sqlSession.insert("replyMapper.insertReply", reply);
	}

	public int updateReply(Reply reply) {
		return sqlSession.update("replyMapper.updateReply", reply);
	}

	public int deleteReply(int reNo) {
		return sqlSession.update("replyMapper.deleteReply", reNo);
	}

	public ArrayList<Board> selectReComments(int boMotherNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectReComments", boMotherNo);
	}

	public int insertReCommnet(Reply reply) {
		return sqlSession.insert("replyMapper.insertReComment", reply);
	}

}
