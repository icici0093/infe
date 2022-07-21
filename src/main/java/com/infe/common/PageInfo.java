package com.infe.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageInfo {
		// 현재 페이지
		private int currentPage;
		// 몇개의 게시글
		private int boardLimit;
		// 몇개의 네비게이션 수
		private int pageLimit;
		// 네비게이션 첫번째 값
		private int startPage;
		// 네비게이션 마지막 값
		private int endPage;
		// 전체 게시글 개수
		private int listCount;
		// 페이지 마지막 번호
		private int maxPage;
		
		public PageInfo() {}

		public PageInfo(int currentPage, int boardLimit, int pageLimit, int startPage, int endPage, int listCount,
				int maxPage) {
			super();
			this.currentPage = currentPage;
			this.boardLimit = boardLimit;
			this.pageLimit = pageLimit;
			this.startPage = startPage;
			this.endPage = endPage;
			this.listCount = listCount;
			this.maxPage = maxPage;
		}

}
