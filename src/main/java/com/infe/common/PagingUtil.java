package com.infe.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author rsmhpdev06
 */
public class PagingUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(PagingUtil.class);
	
	private static int  PAGE_GROUP_SIZE  = 10;		// 한번에 보여줄 페이지 번호
	private static int  PAGE_GROUP_SIZE_AUTOR  = 7; // KDI 저자 팝업  한번에 보여줄 페이지 번호
	
	/**
	 * @Method Name : getStartRow
	 * @작성일 : 2019. 12. 9.
	 * @작성자 : chan
	 * @변경이력 :
	 * @Method 설명 :
	 * @param pg
	 * @param pp
	 * @return
	 */
	public static int getStartRow(int pg, int pp) {
		int startRow = 0;
		startRow = (pg * pp) - pp + 1;
		return startRow;
	}
	
	/**
	 * @Method Name : getEndRow
	 * @작성일 : 2019. 12. 9.
	 * @작성자 : chan
	 * @변경이력 :
	 * @Method 설명 :
	 * @param pg
	 * @param pp
	 * @return
	 */
	public static int getEndRow(int pg, int pp) {
		int endRow = 0;
		endRow = pg * pp;
		return endRow;
	}
	
	/**
	 * @Method Name : Paging
	 * @작성일 : 2019. 12. 11.
	 * @작성자 : chan
	 * @변경이력 :
	 * @Method 설명 : 페이지 이동을 pageFunction 으로 표현.
	 * @param intPrn_Per_Cnt : PP
	 * @param intCurrentPage : PG
	 * @param intTotalRowNum : Totalcount
	 * @return
	 */
	public static String Paging(int intPrn_Per_Cnt, int intCurrentPage, int intTotalRowNum) {
		StringBuffer sbPaging     = new StringBuffer();
		if (intPrn_Per_Cnt != 0) {
			try {
				
				int intVirtualStartPage   = 0;
				int intVirtualEndPage     = 0;
				int intVirtualPrevPage    = 0;
				int intVirtualNextPage    = 0;
				int intFinalPage          = intTotalRowNum/intPrn_Per_Cnt;
				int intRest               = intTotalRowNum%intPrn_Per_Cnt;
				
				if (intRest==0) { }
				else { intFinalPage = intFinalPage+1; }
		
				if (intCurrentPage>PAGE_GROUP_SIZE) { 
					intVirtualStartPage = ((int)((intCurrentPage-1)/PAGE_GROUP_SIZE))*PAGE_GROUP_SIZE+1;
					intVirtualPrevPage  = intVirtualStartPage-1;
				}
				else { intVirtualStartPage = 1; }
		
				intVirtualEndPage = intVirtualStartPage + PAGE_GROUP_SIZE - 1;
				if (intVirtualEndPage>=intFinalPage) { intVirtualEndPage = intFinalPage; }
				else { intVirtualNextPage = intVirtualEndPage+1; } 
				
				// 2019.08.23 처음,마지막 페이지 추가
//				sbPaging.append("<button type=\"button\" class=\"p01\" onclick=\"pageFunction("+ 1 +")\">NEXT</button>");
				
				sbPaging.append("<ul class=\"pagination\" style=\"justify-content: center;\">");
				// 이전 페이지
				if (intVirtualPrevPage>0) {
//					sbPaging.append("<button type=\"button\" class=\"p\" onclick=\"pageFunction("+ intVirtualPrevPage +")\">이전 페이지로 이동</button>");
					sbPaging.append("<li class=\"page-item\">");
					sbPaging.append("<a class=\"page-link\" href=\"javascript:;\" aria-label=\"Previous\" onclick=\"pageFunction("+ intVirtualPrevPage +")\">");
					sbPaging.append("<span aria-hidden=\"true\">&laquo;</span>");
					sbPaging.append("<span class=\"sr-only\"></span>");
					sbPaging.append("</a>");
					sbPaging.append("</li>");
				}
				else {
					sbPaging.append("<li class=\"page-item\">");
					sbPaging.append("<a class=\"page-link disabled\" href=\"javascript:;\" onclick=\"return false;\" style=\"cursor:default;\">");
					sbPaging.append("<span aria-hidden=\"true\">&laquo;</span>");
					sbPaging.append("<span class=\"sr-only\"></span>");
					sbPaging.append("</a>");
					sbPaging.append("</li>");
				}
				
				
				for (int i=intVirtualStartPage; i<intVirtualEndPage+1; i++) {
					if (i==intCurrentPage) { 
						sbPaging.append("<li class=\"page-item active\">");
						sbPaging.append("<a class=\"page-link\" href=\"javascript:;\" onclick=\"return false;\" style=\"cursor:default;\">"+ i +"</a>");
						sbPaging.append("</li>");
					}
					else { 
						sbPaging.append("<li  class=\"page-item\">");
						sbPaging.append("<a class=\"page-link\" href=\"javascript:;\" onclick=\"pageFunction("+ i +");\">"+ i +"</a>");
						sbPaging.append("</li>");
					}
				}
				
				// 다음 페이지
				if (intVirtualNextPage>0) {
					sbPaging.append("<li class=\"page-item\">");
					sbPaging.append("<a class=\"page-link\" href=\"javascript:;\" aria-label=\"Next\" onclick=\"pageFunction("+ intVirtualNextPage +")\">");
					sbPaging.append("<span aria-hidden=\"true\">&raquo;</span>");
					sbPaging.append("<span class=\"sr-only\"></span>");
					sbPaging.append("</a>");
					sbPaging.append("</li>");
				}
				else {
					sbPaging.append("<li class=\"page-item\">");
					sbPaging.append("<a class=\"page-link disabled\" href=\"javascript:;\" onclick=\"return false;\" style=\"cursor:default;\">");
					sbPaging.append("<span aria-hidden=\"true\">&raquo;</span>");
					sbPaging.append("<span class=\"sr-only\"></span>");
					sbPaging.append("</a>");
					sbPaging.append("</li>");
				}
				
				sbPaging.append("</ul>");

				// 2019.08.23 처음,마지막 페이지 추가
//				sbPaging.append("<button type=\"button\" class=\"n02\" onclick=\"pageFunction("+ intFinalPage +")\">NEXT</button>");
				
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return sbPaging.toString();
	}
	
	/**
	 * @Method Name : PagingKDI
	 * @작성일 : 2019. 12. 11.
	 * @작성자 : chan
	 * @변경이력 :
	 * @Method 설명 : 페이지 이동을 pageFunction 으로 표현.
	 * @param intPrn_Per_Cnt : PP
	 * @param intCurrentPage : PG
	 * @param intTotalRowNum : Totalcount
	 * @return
	 */
	public static String PagingKDI(int intPrn_Per_Cnt, int intCurrentPage, int intTotalRowNum) {
		StringBuffer sbPaging     = new StringBuffer();
		if (intPrn_Per_Cnt != 0) {
			try {
				
				int intVirtualStartPage   = 0;
				int intVirtualEndPage     = 0;
				int intVirtualPrevPage    = 0;
				int intVirtualNextPage    = 0;
				int intFinalPage          = intTotalRowNum/intPrn_Per_Cnt;
				int intRest               = intTotalRowNum%intPrn_Per_Cnt;
				
				if (intRest==0) { }
				else { intFinalPage = intFinalPage+1; }
		
				if (intCurrentPage>PAGE_GROUP_SIZE) { 
					intVirtualStartPage = ((int)((intCurrentPage-1)/PAGE_GROUP_SIZE))*PAGE_GROUP_SIZE+1;
					intVirtualPrevPage  = intVirtualStartPage-1;
				}
				else { intVirtualStartPage = 1; }
		
				intVirtualEndPage = intVirtualStartPage + PAGE_GROUP_SIZE - 1;
				if (intVirtualEndPage>=intFinalPage) { intVirtualEndPage = intFinalPage; }
				else { intVirtualNextPage = intVirtualEndPage+1; } 
				
				// 2019.08.23 처음,마지막 페이지 추가
				if(intTotalRowNum > 10) {
					sbPaging.append("<button type=\"button\" onclick=\"pageFunction("+ 1 +")\">PREV</button>");
					
					// 이전 페이지
					if (intVirtualPrevPage>0) sbPaging.append("<button type=\"button\" class=\"prev\" onclick=\"pageFunction("+ intVirtualPrevPage +")\">이전 페이지로 이동</button>");
					else sbPaging.append("<button type=\"button\" class=\"prev\" onclick=\"return false;\">이전 페이지로 이동</button>");
				}
				sbPaging.append("<span>");
				
				for (int i=intVirtualStartPage; i<intVirtualEndPage+1; i++) {
					if (i==intCurrentPage) { 
						sbPaging.append("<a class=\"on\" href=\"javascript:;\" onclick=\"return false;\" style=\"cursor:default;\">"+ i +"</a>");
					}
					else { 
						sbPaging.append("<a href=\"javascript:;\" onclick=\"pageFunction("+ i +");\">"+ i +"</a>");
					}
				}
				
				sbPaging.append("</span>");
				
				if(intTotalRowNum > 10) {
					// 다음 페이지
					if (intVirtualNextPage>0) sbPaging.append("<button type=\"button\" class=\"next\" onclick=\"pageFunction("+ intVirtualNextPage +")\">다음 페이지로 이동</button>");
					else sbPaging.append("<button type=\"button\" class=\"next\" onclick=\"return false;\">다음 페이지로 이동</button>");
					
					// 2019.08.23 처음,마지막 페이지 추가
					sbPaging.append("<button type=\"button\" class=\"n02\" onclick=\"pageFunction("+ intFinalPage +")\">NEXT</button>");
				}
				
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return sbPaging.toString();
	}
	
	/**
	 * @Method Name : SharePagingPP9
	 * @작성일 : 2019. 12. 11.
	 * @작성자 : chan
	 * @변경이력 :
	 * @Method 설명 : 페이지 이동을 pageFunction 으로 표현. ( pp 기본값이 9인 페이지에 적용)
	 * @param intPrn_Per_Cnt : PP
	 * @param intCurrentPage : PG
	 * @param intTotalRowNum : Totalcount
	 * @return
	 */
	public static String SharePagingPP9(int intPrn_Per_Cnt, int intCurrentPage, int intTotalRowNum) {
		StringBuffer sbPaging     = new StringBuffer();
		if (intPrn_Per_Cnt != 0) {
			try {
				
				int intVirtualStartPage   = 0;
				int intVirtualEndPage     = 0;
				int intVirtualPrevPage    = 0;
				int intVirtualNextPage    = 0;
				int intFinalPage          = intTotalRowNum/intPrn_Per_Cnt;
				int intRest               = intTotalRowNum%intPrn_Per_Cnt;
				
				if (intRest==0) { }
				else { intFinalPage = intFinalPage+1; }
				
				if (intCurrentPage>PAGE_GROUP_SIZE) { 
					intVirtualStartPage = ((int)((intCurrentPage-1)/PAGE_GROUP_SIZE))*PAGE_GROUP_SIZE+1;
					intVirtualPrevPage  = intVirtualStartPage-1;
				}
				else { intVirtualStartPage = 1; }
				
				intVirtualEndPage = intVirtualStartPage + PAGE_GROUP_SIZE - 1;
				if (intVirtualEndPage>=intFinalPage) { intVirtualEndPage = intFinalPage; }
				else { intVirtualNextPage = intVirtualEndPage+1; } 
				
				// 2019.08.23 처음,마지막 페이지 추가
				if(intTotalRowNum > 9) {
					sbPaging.append("<button type=\"button\" onclick=\"pageFunction("+ 1 +")\">PREV</button>");
					
					// 이전 페이지
					if (intVirtualPrevPage>0) sbPaging.append("<button type=\"button\" class=\"prev\" onclick=\"pageFunction("+ intVirtualPrevPage +")\">이전 페이지로 이동</button>");
					else sbPaging.append("<button type=\"button\" class=\"prev\" onclick=\"return false;\">이전 페이지로 이동</button>");
					
				}
				sbPaging.append("<span>");
				
				
				for (int i=intVirtualStartPage; i<intVirtualEndPage+1; i++) {
					if (i==intCurrentPage) { 
						sbPaging.append("<a class=\"on\" href=\"javascript:;\" onclick=\"return false;\" style=\"cursor:default;\">"+ i +"</a>");
					}
					else { 
						sbPaging.append("<a href=\"javascript:;\" onclick=\"pageFunction("+ i +");\">"+ i +"</a>");
					}
				}
				
				sbPaging.append("</span>");
				
				if(intTotalRowNum > 9) {
					// 다음 페이지
					if (intVirtualNextPage>0) sbPaging.append("<button type=\"button\" class=\"next\" onclick=\"pageFunction("+ intVirtualNextPage +")\">다음 페이지로 이동</button>");
					else sbPaging.append("<button type=\"button\" class=\"next\" onclick=\"return false;\">다음 페이지로 이동</button>");
					
					// 2019.08.23 처음,마지막 페이지 추가
					sbPaging.append("<button type=\"button\" class=\"n02\" onclick=\"pageFunction("+ intFinalPage +")\">NEXT</button>");
				}
				
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return sbPaging.toString();
	}

	
	/**
	 * @Method Name : PagingKDI
	 * @작성일 : 2019. 12. 11.
	 * @작성자 : chan
	 * @변경이력 :
	 * @Method 설명 : 페이지 이동을 pageFunction 으로 표현.
	 * @param intPrn_Per_Cnt : PP
	 * @param intCurrentPage : PG
	 * @param intTotalRowNum : Totalcount
	 * @return
	 */
	public static String PagingKDI_AuthorReport(int intPrn_Per_Cnt, int intCurrentPage, int intTotalRowNum) {
		StringBuffer sbPaging     = new StringBuffer();
		if (intPrn_Per_Cnt != 0) {
			try {
				
				int intVirtualStartPage   = 0;
				int intVirtualEndPage     = 0;
				int intVirtualPrevPage    = 0;
				int intVirtualNextPage    = 0;
				int intFinalPage          = intTotalRowNum/intPrn_Per_Cnt;
				int intRest               = intTotalRowNum%intPrn_Per_Cnt;
				
				if (intRest==0) { }
				else { intFinalPage = intFinalPage+1; }
		
				if (intCurrentPage>PAGE_GROUP_SIZE_AUTOR) { 
					intVirtualStartPage = ((int)((intCurrentPage-1)/PAGE_GROUP_SIZE_AUTOR))*PAGE_GROUP_SIZE_AUTOR+1;
					intVirtualPrevPage  = intVirtualStartPage-1;
				}
				else { intVirtualStartPage = 1; }
		
				intVirtualEndPage = intVirtualStartPage + PAGE_GROUP_SIZE_AUTOR - 1;
				if (intVirtualEndPage>=intFinalPage) { intVirtualEndPage = intFinalPage; }
				else { intVirtualNextPage = intVirtualEndPage+1; } 
				
				// 2019.08.23 처음,마지막 페이지 추가
				sbPaging.append("<button type=\"button\" data-ajax-url=\"/research/authorReportList\" onclick=\"pageFunctionReport("+ 1 +", this)\">NEXT</button>");
				
				// 이전 페이지
				if (intVirtualPrevPage>0) sbPaging.append("<button type=\"button\" class=\"prev\" data-ajax-url=\"/research/authorReportList\" onclick=\"pageFunctionReport("+ intVirtualPrevPage +", this)\">이전 페이지로 이동</button>");
				else sbPaging.append("<button type=\"button\" class=\"prev\" onclick=\"return false;\">이전 페이지로 이동</button>");
				
				sbPaging.append("<span>");
				
				for (int i=intVirtualStartPage; i<intVirtualEndPage+1; i++) {
					if (i==intCurrentPage) { 
						sbPaging.append("<a class=\"on\" href=\"javascript:;\" onclick=\"return false;\" style=\"cursor:default;\">"+ i +"</a>");
					}
					else { 
						sbPaging.append("<a href=\"javascript:;\" data-ajax-url=\"/research/authorReportList\" onclick=\"pageFunctionReport("+ i +", this);\">"+ i +"</a>");
					}
				}
				
				sbPaging.append("</span>");
				
				// 다음 페이지
				if (intVirtualNextPage>0) sbPaging.append("<button type=\"button\" class=\"next\" data-ajax-url=\"/research/authorReportList\" onclick=\"pageFunctionReport("+ intVirtualNextPage +", this)\">다음 페이지로 이동</button>");
				else sbPaging.append("<button type=\"button\" class=\"next\" onclick=\"return false;\">다음 페이지로 이동</button>");
				
				// 2019.08.23 처음,마지막 페이지 추가
				sbPaging.append("<button type=\"button\" class=\"n02\" data-ajax-url=\"/research/authorReportList\" onclick=\"pageFunctionReport("+ intFinalPage +", this)\">NEXT</button>");
				
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return sbPaging.toString();
	}
	
	/**
	 * @Method Name : PagingKDI
	 * @작성일 : 2019. 12. 11.
	 * @작성자 : chan
	 * @변경이력 :
	 * @Method 설명 : 페이지 이동을 pageFunction 으로 표현.
	 * @param intPrn_Per_Cnt : PP
	 * @param intCurrentPage : PG
	 * @param intTotalRowNum : Totalcount
	 * @return
	 */
	public static String PagingKDI_AuthorEtc(int intPrn_Per_Cnt, int intCurrentPage, int intTotalRowNum) {
		StringBuffer sbPaging     = new StringBuffer();
		if (intPrn_Per_Cnt != 0) {
			try {
				
				int intVirtualStartPage   = 0;
				int intVirtualEndPage     = 0;
				int intVirtualPrevPage    = 0;
				int intVirtualNextPage    = 0;
				int intFinalPage          = intTotalRowNum/intPrn_Per_Cnt;
				int intRest               = intTotalRowNum%intPrn_Per_Cnt;
				
				if (intRest==0) { }
				else { intFinalPage = intFinalPage+1; }
		
				if (intCurrentPage>PAGE_GROUP_SIZE_AUTOR) { 
					intVirtualStartPage = ((int)((intCurrentPage-1)/PAGE_GROUP_SIZE_AUTOR))*PAGE_GROUP_SIZE_AUTOR+1;
					intVirtualPrevPage  = intVirtualStartPage-1;
				}
				else { intVirtualStartPage = 1; }
		
				intVirtualEndPage = intVirtualStartPage + PAGE_GROUP_SIZE_AUTOR - 1;
				if (intVirtualEndPage>=intFinalPage) { intVirtualEndPage = intFinalPage; }
				else { intVirtualNextPage = intVirtualEndPage+1; } 
				
				// 2019.08.23 처음,마지막 페이지 추가
				sbPaging.append("<button type=\"button\" data-ajax-url=\"/research/authorEtcList\" onclick=\"pageFunctionEtc("+ 1 +", this)\">NEXT</button>");
				
				// 이전 페이지
				if (intVirtualPrevPage>0) sbPaging.append("<button type=\"button\" class=\"prev\" data-ajax-url=\"/research/authorEtcList\" onclick=\"pageFunctionEtc("+ intVirtualPrevPage +", this)\">이전 페이지로 이동</button>");
				else sbPaging.append("<button type=\"button\" class=\"prev\" onclick=\"return false;\">이전 페이지로 이동</button>");
				
				sbPaging.append("<span>");
				
				for (int i=intVirtualStartPage; i<intVirtualEndPage+1; i++) {
					if (i==intCurrentPage) { 
						sbPaging.append("<a class=\"on\" href=\"javascript:;\" onclick=\"return false;\" style=\"cursor:default;\">"+ i +"</a>");
					}
					else { 
						sbPaging.append("<a href=\"javascript:;\" data-ajax-url=\"/research/authorEtcList\" onclick=\"pageFunctionEtc("+ i +", this);\">"+ i +"</a>");
					}
				}
				
				sbPaging.append("</span>");
				
				// 다음 페이지
				if (intVirtualNextPage>0) sbPaging.append("<button type=\"button\" class=\"next\" data-ajax-url=\"/research/authorEtcList\" onclick=\"pageFunctionEtc("+ intVirtualNextPage +", this)\">다음 페이지로 이동</button>");
				else sbPaging.append("<button type=\"button\" class=\"next\" onclick=\"return false;\">다음 페이지로 이동</button>");
				
				// 2019.08.23 처음,마지막 페이지 추가
				sbPaging.append("<button type=\"button\" class=\"n02\" data-ajax-url=\"/research/authorEtcList\" onclick=\"pageFunctionEtc("+ intFinalPage +", this)\">NEXT</button>");
				
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return sbPaging.toString();
	}
	
}