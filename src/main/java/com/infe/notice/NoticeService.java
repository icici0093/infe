package com.infe.notice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infe.common.PageInfo;

@Service
public class NoticeService{
	
	@Autowired
	public NoticeStore nStore;
	
	public int getListCount(Notice notice) {
		return nStore.selectListCount(notice);
	}

	public ArrayList<Notice> printAll(PageInfo pi, Notice notice) {
		return nStore.selectList(pi, notice);
	}

	public int registerNotice(Notice notice) {
		return nStore.insertNotice(notice);
	}

	public int addReadCount(int noNo) {
		return nStore.addReadCount(noNo);
	}

	public Notice printOne(int noNo) {
		return nStore.selectOne(noNo);
	}

	public int removeNotice(int noNo) {
		return nStore.deleteNotice(noNo);
	}

	public int getPageCount(Search search) {
		return nStore.selectPageCount(search);
	}

	public ArrayList<Notice> printSearchAll(PageInfo pi, Search search) {
		return nStore.selectSearchList(pi, search);
	}

	public ArrayList<Notice> readNoticeNum(Notice notice) {
		return nStore.readNoticeNum(notice);
	}

	public int modifyNotice(Notice notice) {
		return nStore.updateNotice(notice);
	}

	public ArrayList<Notice> readUpdateDate(Notice notice) {
		return nStore.readUpdateDate(notice);
	}

}
