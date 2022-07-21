package com.infe.common;


public class JsUtil   {

	public static String Outjs(String strObj) {
		StringBuffer	strJs 	= new StringBuffer();
		strJs.append("<script type=\"text/javascript\"> \n");
		strJs.append(strObj + "\n");
		strJs.append("</script>");
		return strJs.toString();
	}
	
	/**
	 * 자바스크립트 액션을 처리함
	 * 
	 * @param strAlert 액션 정 alert 메세지(없으면 처리하지 않음
	 * @param strLocation 이동할 주소
	 * @param jstype 액션 타입 구분(-1 : 이전화면, 0 : 창닫기, 기본 : 이동
	 * @return String
	 */
	public static String OutLocationjs(String strAlert, String strLocation, String jstype) {
		StringBuffer	strJs 	= new StringBuffer();
		jstype = StrUtil.nvl(jstype);
		strJs.append("<script type=\"text/javascript\"> \n");
		if (!StrUtil.nvl(strAlert).equals("")) {
			strJs.append("alert(\""+strAlert+"\"); \n");
		}
		if ("-1".equals(jstype)) strJs.append("history.back(-1); \n");
		else if ("0".equals(jstype)) strJs.append("self.close(); \n");
		else {
			if (!"".equals(strLocation))
				strJs.append("location.href=\""+strLocation+"\"; \n");
		}
		strJs.append("</script>");
		return strJs.toString();
	}	
}
