package com.infe.common;

import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 문자열관련 유틸 클래스.
 * 
 * @author dolgamza
 *
 */

public class StrUtil   {


	
	/***************************************************************************
	 * 
	 */
	
	
	/**
	 * NULL체크
	 * 
	 * @param strObj 확인할 문자열
	 * @return boolean
	 */
	public final static boolean validateStringCheck(String strObj) {
		if (strObj==null || strObj.trim().length() == 0) { return false; }
		return true;
	}
	
	/**
	 * 문자열인지 확인
	 * 
	 * @param strObj 확인할 문자열
	 * @return boolean
	 */
	public final static boolean isAlphaNumeric(String strObj) {
		for (int i=0; i<strObj.length(); i++) {
			if (!Character.isLetterOrDigit(strObj.charAt(i))) { return (false); }
		}
		return (true);
	}
	
	public static String stringToHex(String str) { 
		char[] chars = str.toCharArray();
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			strBuffer.append(Integer.toHexString((int) chars[i]));
		}
		return strBuffer.toString();
	}		

	/**
	 * 숫자열 체크
	 * 
	 * @param strObj 확인할 문자열
	 * @return boolean
	 */
	public final static boolean isOnlyNumeric(String strObj) {
		for (int i = 0; i < strObj.length(); i++) {
			if (!Character.isDigit(strObj.charAt(i))) { return (false); }
		}
		return (true);
	}	

	public static String nvl(String strObj, String strReplace) {
		return (NullCheck.nvl(strObj)==true) ? strReplace:strObj; 
	}
	
	
	public static String nvl(String strObj) {
		String rtnstr = "";
		rtnstr =  (NullCheck.nvl(strObj)==true) ? "":strObj;
		if (rtnstr.equals("null")) rtnstr = "";
		return rtnstr;
	}
	
	public static String convWithNumberFormat(String strPattern, double dblSrc) {
		String strResult = "";
		java.text.DecimalFormat form1 = new java.text.DecimalFormat(strPattern);
		strResult = form1.format(dblSrc);
		return strResult;
	}

	public static String convWithNumberFormat(String strPattern, int intSrc) {
		String strResult = "";
		java.text.DecimalFormat form1 = new java.text.DecimalFormat(strPattern);
		strResult = form1.format(intSrc);
		return strResult;
	}


	
	
	public static String replaceString(String strSearch, String strReplace, String strSource) {
		int spot;
		String returnString;
		String origSource = new String(strSource);
		spot = strSource.indexOf(strSearch);
		if (spot > -1) { returnString = ""; }
		else { returnString = strSource; }
		while (spot > -1) {
			if (spot == strSource.length() + 1) {
				returnString = returnString.concat(strSource.substring(0, strSource.length() - 1).concat(strReplace));
				strSource = "";
			}
			else if (spot > 0) {
				returnString = returnString.concat(strSource.substring(0, spot).concat(strReplace));
				strSource = strSource.substring(spot + strSearch.length(), strSource.length());
			}
			else {
				returnString = returnString.concat(strReplace);
				strSource = strSource.substring(spot + strSearch.length(), strSource.length());
			}
			spot = strSource.indexOf(strSearch);
		}
		if (!strSource.equals(origSource)) { return returnString.concat(strSource); }
		else { return returnString; }
	}
	
	
	
	/**************
	 * 배열<>스트링변환 
	 */
	
	
	/**
	 * 스트링을 주어진 구분자로 나눈뒤 결과를 Vector로 반환
	 * @param strDelimiter 나누고자하는 delimiter. delimiter는 제외가 됨으로 벡터에 들어가지않음
	 * @param strObj 나누어야 할 스트링
	 * @return 토큰의 배열
	 */
	public final static String[] splitString(String strDelimiter, String strObj) {
		String array[] = null;
		try{
		Vector<String> v = new Vector<String>();
			StringTokenizer st = new StringTokenizer(strObj, strDelimiter);
			while (st.hasMoreTokens()) { v.addElement(st.nextToken()); }
			array = new String[v.size()];
			v.copyInto(array);
		}catch(Exception e){}
		
		return (array);
	}

	/**
	 * 토큰의 배열의 중간에 구분자로 넣어 합친뒤 스트링으로 반환
	 * @param strDelimiter 토큰이 합쳐질때 사이에 들어갈 delimiter.
	 * @param array 합쳐야할 토큰들.
	 * @return 합쳐진 스트링.
	 */
	public final static String joinString(String strDelimiter, String array[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (i > 0) sb.append(strDelimiter);
			sb.append(array[i]);
		}
		return (sb.toString());
	}
	



	public static String fnEng2Kor(String strEnglish) {
		return Charkoreng.fnEng2Kor(strEnglish);
	}

	public static String fnKor2Eng(String strKorean) {
		return Charkoreng.fnKor2Eng(strKorean);
	}

	
	/**
	 * 문자열 자르기(게시판 제목 등에 사용됨)
	 * @param strObj 자를 문자열
	 * @param intLength 자를 길이
	 * @param intLevel Depth에 맞추기
	 * @param strTail 잘릴 문자 뒤에 붙을 문자열
	 * @return 문자열
	 */
	public static String getFitString(String strObj, int intLength, int intLevel, String strTail) {
		int intBlankLen = 0;
		String strReturn = null;
		if (intLevel!=0) { intBlankLen = intLength-((intLevel+1)*2); }
		else { intBlankLen = intLength; }
		if (intBlankLen<1) { intBlankLen = 1; }
		if (strObj.length()>=intBlankLen && intBlankLen>0) { strReturn = cutString(strObj,intBlankLen,strTail); }
		else { strReturn = strObj; }
		return strReturn;
	}
	
	/**
	 * 문자열 자르기
	 * 
	 * @param strObj 자를 문자열
	 * @param intObjLength 자를 길이
	 * @return 문자열 
	 */
	public static String cutStringmid(String strObj, int intObjStartLength, int intObjEndLength, String strTail) {
		int intStringLength  = strObj.length();
		if (intStringLength > intObjStartLength) {
//			char chrObj;
			int intStringCutPos=0;
			int intMax=intObjEndLength;
			intStringCutPos=intMax; 
//			chrObj = strObj.charAt(intMax);
//			if (chrObj>=128) intStringCutPos--;
			strObj = strObj.substring(intObjStartLength,intStringCutPos) + strTail;
		}
		else { }
		return strObj;
	}

	/**
	 * 문자열 자르기
	 * 
	 * @param strObj 자를 문자열
	 * @param intObjLength 자를 길이
	 * @return 문자열 
	 */
	public static String cutString(String strObj, int intObjLength, String strTail) {
		int intStringLength  = strObj.length();
		if (intStringLength > intObjLength) {
//			char chrObj;
			int intStringCutPos=0;
			int intMax=intObjLength;
			intStringCutPos=intMax; 
//			chrObj = strObj.charAt(intMax);
//			if (chrObj>=128) intStringCutPos--;
			strObj = strObj.substring(0,intStringCutPos) + strTail;
		}
		else { }
		return strObj;
	}
	
	public static String getCutTail(String strObj, int intLength){
		return getCutTail(strObj, intLength, "*");
	}
	/**
	 * 문자열 자르기 뒷자리 안보이게 하기
	 * @param strObj 처리할 문자열
	 * @param intLength 안보이게 할 문자길이
	 * @param strTail 잘릴 문자 뒤에 붙을 문자열
	 * @return 문자열
	 */
	public static String getCutTail(String strObj, int intLength, String strTail){
		String strReturn = "";
		try{
			int intStringLength  = strObj.length();
			if(intStringLength > intLength){
				strReturn = strObj.substring(0, intStringLength-intLength);
				for(int i=0; i<intLength; i++){
					strReturn = strReturn + strTail;
				}
			}
		}catch(Exception e) {}
		return strReturn;
	}
	
	/* 실수 및 화페단위등에 3자리마다 콤마(,)를 추가하고자  할때  사용한다.
	 * 추가 : 박병선 2006. 4. 20 */
	public static String addComma(String str){
		if(str.length() < 1){
	        return "";
	    }else{
	        String tm = "";
	        boolean negative = true;
			String tm1 = "";
			String tm2 = "";
			
			// 음수 or 양수?
	        if("-".equals(str.substring(0, 1))){
	            tm = str.substring(1, str.length());
	            negative = true;
	        }else{
	            tm = str;
	            negative = false;
	        }

			//실수경우 . 을 기준으로 그 앞에만 comma 를 붙임
			int idx = 0;
			boolean isReal = false;//실수여부
			for(int i=0; i<tm.length(); i++){			
				if(tm.charAt(i)=='.'){
					idx = i;
					isReal = true;
					break;
				}
			}//end for
			if(isReal){
				tm1 = tm.substring(0, idx);
				tm2 = tm.substring(idx+1, tm.length());
				tm = tm1;
			}

	        String st = "";
	        String cm = ",";

	        for(int i = tm.length(), j = 0; i > 0; i--, j++) {
	        	if ((j % 3) == 2) {
	                if (tm.length() == j + 1) st = tm.substring(i - 1, i) + st;
	                else st = cm + tm.substring(i - 1, i) + st;
	            }else{
	                st = tm.substring(i - 1, i) + st;
	            }
	        }

	        if(negative){
				st = "-" + st;
			}

			if(isReal){//실수경우
				st +=  "."+ tm2;
			}
			
			return st;
		}
	}
	
	/**
	 * 성별 가지고 오기
	 * @param 주민번호
	 * @return M : 남, F :여
	 */
	public static String getSex(String sn){
		String strTmp = null;
		String result = null;
		
		int snLen  = sn.length();
		if(snLen == 7){
			strTmp = sn.substring(0,1);
		}else if(snLen == 13){
			strTmp = sn.substring(6,7);
		}

		if("1".equals(strTmp) || "3".equals(strTmp) || "5".equals(strTmp) || "7".equals(strTmp))
			result ="M";
		else
			result ="F";
		return result;
	}

	/**
	 * 회원가입시 사용함
	 * @param 코드값
	 * @return 코드에 ","를 붙여서 string으로 넘긴다.
	 * 			99번 코드는 기타 입력사항이 있음.(,) 구분자 사용
	 * 			00번이 있을경우 00만 리턴
	 */
	public static String addCommaData(String []code, String etc){
		String result = "";
		
		if(code == null || code.length ==0) return result;
		
		boolean code00 = false;
		boolean code99 = false;
		for(int i=0; i<code.length; i++){
			if("00".equals(code[i])){
				code00 = true;
				break;
			}else if("99".equals(code[i])){
				code99 = true;
			}
			if(i == 0){
				result = result + code[i];
			}else{
				result = result + "," +code[i];
			}
		}

		if(code00) result = "00";
		if(code99) result = result + "," + etc;
		
		return result;
	}
	
	public static int parseInt(String s)
	{
		return parseInt(s, -1);
	}
	public static int parseInt(String s, int i)
	{
		int j = i;
		try
		{
			j = Integer.parseInt(s);
		}
		catch(Exception _ex)
		{
			j = i;
		}
			return j;
	}
	
	/**
	 * <PRE>
	 * 두 날짜의 간격을 계산하여 리턴함.
	 * </PRE>
	 * @param	dateCompare	String	비교할 날짜 스트링(yyyyMMddHHmmss) - 기준이 됨
	 * @param	dateCompared	String 비교될 날짜 스트링(yyyyMMddHHmmss)
	 * @param	type	int return value 의 유형
	 * @			1. Calendar.DATE
	 * @			2. Calendar.HOUR
	 * @			3. Calendar.MINUTE
	 * @			4. Calendar.SECOND
	 * @			5. Calendar.MILLISECOND
	 * @return   long	interval between two times
	 */
	//@SuppressWarnings("finally")
	public static long getTimeInterval(String dateCompare, String dateCompared, int type) {
		long interval = 0L;
		if(dateCompare.length() < 14 || dateCompared.length() < 14) return interval;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			interval = formatter.parse(dateCompare).getTime() - formatter.parse(dateCompared).getTime();
			switch (type) {
				case Calendar.MILLISECOND:
					break;
				case Calendar.SECOND:
					interval = interval / 1000;
					break;
				case Calendar.MINUTE:
					interval = interval / 1000 / 60;
					break;
				case Calendar.HOUR:
					interval = interval / 1000 / 60 / 60;
					break;
				case Calendar.DATE:
					interval = interval / 1000 / 60 / 60 / 24;
					break;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return interval;
	}

	/**
	 * 배열에 들어있는 값을 읽어 같은값이 있으면 checked 리턴
	 * @param 예)list("03,99,태국")
	 * @param 예) "03", "etc"        기타일경우 etc를 입력하여 값을 얻는다.
	 * @return  checked
	 */
	public static String getCheckBox(String list, String code){
		String result ="";
		try{
		String tmp[] = splitString(",", list);
			for(int i=0; i<tmp.length; i++){
				if(code.equals(tmp[i])){
					result = "checked";
				}
				if(("etc".equals(code)) && ("99".equals(tmp[i]))){	//99다음에 기타내용이 들어있음.
					if(tmp.length > i){
						result = tmp[i+1];
					}
				}
			}
		}catch(Exception e){
		}
		return result;
	}
	
	/**
	 * 배열에 들어있는 값을 읽어 같은값이 있으면 checked 리턴
	 * @param 예)list("03,99")
	 * @param 예) "03"
	 * @return  checked
	 */
	public static String getMyPageCheckBox(String list, String code){
		String result ="off";
		try{
		String tmp[] = splitString(",", list);
			for(int i=0; i<tmp.length; i++){
				if(code.equals(tmp[i])){
					result = "on";
				}
			}
		}catch(Exception e){
		}
		return result;
	}
	/**
	 * 주민번호를 받아서 - 을 포함하여 리턴함.
	 * @param 예) sn 1111112222222
	 * @return  111111-2222222
	 */
	public static String getSn(String sn, boolean cutTail){
		String result="";
		if((sn == null) || (sn.length()!=13)) return sn;
		
		if(cutTail)	sn = getCutTail(sn, 7);
	
		result = sn.substring(0,6) + "-" + sn.substring(6,13);
		
		return result;
	}
	
	public static String enterToBr(String str) throws IOException
	{
		str =nvl(str);
		str = str.replaceAll("[\n]", "<br />");
		return str;
		
//		StringTokenizer st = new StringTokenizer(str, "\r\n");
//		StringBuffer sb = new StringBuffer();
//		if(str != null)
//		{
//			while(st.hasMoreTokens())
//			{
//				sb.append(st.nextToken());
//				sb.append("<br />");
//			}
//		}
//		return sb.toString();
	}
	
	public static String htmltextchk(String str)
	{
		if (str.indexOf("<br>")<=0 && str.indexOf("</TABLE>")<=0 && str.indexOf("</table>")<=0) str = replaceString("\n", "<br />",str); 
		return str;
	}
	
	public static String enterToSpace(String str) throws IOException
	{
		StringTokenizer st = new StringTokenizer(str, "\n");
		StringBuffer sb = new StringBuffer();
		if(str != null)
		{
			while(st.hasMoreTokens())
			{
				sb.append(st.nextToken());
				sb.append("");
			}
		}
		return sb.toString();
	}
	
//	public static void writeClobDate(ResultSet rs, String text){
//		try{
//			CLOB clob = ((OracleResultSet)rs).getCLOB(1);
//			Writer writer = clob.getCharacterOutputStream(); 
//			Reader src = new CharArrayReader(text.toCharArray()); 
//			char[] buffer = new char[1024];
//			int read = 0;   
//			
//			// CLOB Data insert
//			while ( (read = src.read(buffer,0,1024)) != -1) { 
//				writer.write(buffer, 0, read); // write clob. 
//			}
//			src.close();
//			writer.close();
//		}catch(Exception e){}
//	}
	
	public static String readClobData(Reader reader) throws IOException
	{
		StringBuffer data = new StringBuffer(); 
		
		char[] buf = new char[1024];
		int cnt=0;
		
		if(reader!=null) {
			while((cnt=reader.read(buf)) != -1) {
			data.append(buf,0,cnt);
			}
		}
		return data.toString();
	}
	
	public static String addZero(String str) {
		if((str!=null) && (str.length() > 0)){
			if(".".equals(str.substring(0,1))){
				str = "0" + str;
			}
		}
		return str;
	}
	
	public static String FillZero(int num, int leng) {
		String numformat = "0000000000000000000000000000000000000000000000000000000";
		leng = StrUtil.parseInt(leng+"", 0);
		if (leng > 20) leng = 20;
		numformat = numformat + num;
		
		return numformat.substring(numformat.length()-leng);
	}	
	
	//String strObj, int intLength, String strTail){
	public static void main(String args[]){
		double tmp = 99.99000000000001;
		
		System.out.println((convWithNumberFormat("###.00", tmp)));
	}
	
	
	//String strObj, int intLength, String strTail){
	public static String StrAddZero(String Numstr, int Zleng){
		String ren_str = "";
		for (int k=0;k<Zleng;k++)
		{
			ren_str = ren_str + "0";
		}
		ren_str = ren_str + Numstr;
		ren_str = ren_str.substring(ren_str.length() - Zleng);
		return ren_str;
	}
	
	
	public static String markKeyword(String text, String keyword, String Font_color, boolean bold_flag) {
	    if (keyword == null) // 키워드가 존재하지 않으면 ...
	      return text;
	    String Return_Txt = "";
	    String Replace_tag = "";
	    Replace_tag = keyword;
	    if (bold_flag) Replace_tag = "<b>"+Replace_tag+"</b>";
	    if (!"".equals(Font_color)) Replace_tag = "<font color=\""+Font_color+"\">"+Replace_tag+"</font>";
	    Return_Txt = text.replaceAll(keyword, Replace_tag);    
	    return Return_Txt;
	}
	
	public static String markKeyword2(String text, String keyword, String Span_Style) {
	    if (keyword == null) // 키워드가 존재하지 않으면 ...
	      return text;
	    String Return_Txt = "";
	    String Replace_tag = "";
	    Replace_tag = keyword;
	    if (!"".equals(Span_Style)) Replace_tag = "<span class=\""+Span_Style+"\">"+Replace_tag+"</span>";
	    if (!"".equals(keyword.trim()))
	    	Return_Txt = text.replaceAll(keyword, Replace_tag);
	    Return_Txt = text.replaceAll("&","&amp;");
	    return Return_Txt;
	}	

	public static String markKeyword2(String text, String keyword, String Span_Style, int text_length) {
	    if (keyword == null) // 키워드가 존재하지 않으면 ...
	      return text;
	    String Return_Txt = "";
	    String Replace_tag = "";
	    int Text_Mid = 0;
	    int Cut_Length = 0;
	    if (text.length() < text_length) Cut_Length = text.length()-1;
	    else Cut_Length = text_length;
	    Replace_tag = keyword;
	    if (!"".equals(Span_Style)) Replace_tag = "<span class=\""+Span_Style+"\">"+Replace_tag+"</span>";
	    if (text.indexOf(keyword) != -1) {
	    	Text_Mid = text_length / 2;
	    	if (text.indexOf(keyword) <= Text_Mid) Text_Mid = 0;
	    	else Text_Mid = text.indexOf(keyword) - Text_Mid;
	    	text = text.substring(Text_Mid, Cut_Length);
	    }
	    Return_Txt = text.replaceAll(keyword, Replace_tag);    
	    return Return_Txt;
	}	
	
	
	public static String cycleNum(String num_str) {
		num_str = nvl(num_str);
		String ren_str = "";
		if ("1".equals(num_str))	ren_str = "①";
		else if ("2".equals(num_str))	ren_str = "②";
		else if ("3".equals(num_str))	ren_str = "③";
		else if ("4".equals(num_str))	ren_str = "④";
		else if ("5".equals(num_str))	ren_str = "⑤";
		else if ("6".equals(num_str))	ren_str = "⑥";
		else if ("7".equals(num_str))	ren_str = "⑦";
		else if ("8".equals(num_str))	ren_str = "⑧";
		else if ("9".equals(num_str))	ren_str = "⑨";
		else if ("10".equals(num_str))	ren_str = "⑩";
	    return ren_str;
	}	
	
	
	public static String encodeStr(String encode_str) {
		String rtnstr = encode_str;
		rtnstr = rtnstr.replace("+", "-").replace("/", "_");
	    return rtnstr;
	}		
	
	public static String decodeStr(String decode_str) {
		String rtnstr = decode_str;
		rtnstr = rtnstr.replace("-", "+").replace("_", "/");
	    return rtnstr;
	}			
	
	/**
	 * 랜덤한 문자열을 원하는 길이만큼 반환합니다.
	 * 
	 * @param length 문자열 길이
	 * @return 랜덤문자열
	 */
	
	public static String getRandomString(int length)
	{
	  StringBuffer buffer = new StringBuffer();
	  Random random = new Random();
	 
	  String chars[] = 
	    "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
	 
	  for (int i=0 ; i<length ; i++)
	  {
	    buffer.append(chars[random.nextInt(chars.length)]);
	  }
	  return buffer.toString();
	}	
	
	/**
	 * 랜덤한 숫자문자열을 원하는 길이만큼 반환합니다.
	 * 
	 * @param length 문자열 길이
	 * @return 랜덤문자열
	 */
	public static String getRandomNum(int length)
	{
	  StringBuffer buffer = new StringBuffer();
	  Random random = new Random();
	 
	  String chars[] = 
	    "1,2,3,4,5,6,7,8,9,0".split(",");
	 
	  for (int i=0 ; i<length ; i++)
	  {
	    buffer.append(chars[random.nextInt(chars.length)]);
	  }
	  return buffer.toString();
	}		
	
	private static interface Patterns { 
		// javascript tags and everything in between 
		public static final Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", Pattern.DOTALL); 
		public static final Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>", Pattern.DOTALL); 
		// HTML/XML tags 
		public static final Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>"); 
		//public static final Pattern nTAGS = Pattern.compile("<\\w+\\s+[^<]*\\s*>"); 
		// entity references 
		public static final Pattern ENTITY_REFS = Pattern.compile("&[^;]+;"); 
		// repeated whitespace 
		public static final Pattern WHITESPACE = Pattern.compile("\\s\\s+"); 
	} 

		/** 
		* Clean the HTML input. 
		*/ 
	public static String cleanTag(String s) { 
		if (s == null) { 
			return null; 
		} 
		Matcher m; 
		m = Patterns.SCRIPTS.matcher(s); 
		s = m.replaceAll(""); 
		m = Patterns.STYLE.matcher(s); 
		s = m.replaceAll(""); 
		m = Patterns.TAGS.matcher(s); 
		s = m.replaceAll(""); 
		m = Patterns.ENTITY_REFS.matcher(s); 
		s = m.replaceAll(""); 
		m = Patterns.WHITESPACE.matcher(s); 
		s = m.replaceAll(" "); 
		return s; 
	} 

	public static String rtrim(String str){
		if (str == null) return ""; 
		int len = str.length();
		while (str.charAt(len-1) == ' '){
			str = str.substring(0, len-1);
			len--;
		}
		return str; 
	}
	
    /**
     * 문자열의 일부를 다른 문자로 치완
	 *
	 * @param str 원본 문자열(예 - "HelloWorld")
	 * @param i 치완 하고자 하는 갯수
	 * @param chgchr 치환할 문자열
	 * @return 잘린 문자열 리턴(예 - "He***World")
     */	
	public static String chgString(String str, int i, String chgchr) { 
		if ( str == null ) 
		  return ""; 
		String tmp = ""; 
		int ccount = i; 
		for (int k = 0; k < str.length() ; k++) {
			if (k > 1 && ccount > 0) {
				tmp = tmp + chgchr;
				ccount--;
			}
			else {
				tmp = tmp + str.substring(k,k+1);
			}
		}
		return tmp; 
	}		
	
    /**
     * 문자열 배열을 ,로 구분하여 연결
     */	
	public static String ArrtoString(String str[]) { 
		if ( str == null )   return "";
		String tmp = ArrtoString("",str);
		return tmp; 
	}		
	
    /**
     * 문자열 배열을 ,로 구분하여 연결
     */	
	public static String ArrtoString(String defstr, String str[]) { 
		if ( str == null )   return "";
		String tmp = "";
		for (int k = 0; k < str.length ; k++) {
			if (k == 0) tmp = defstr+str[k];
			else tmp = tmp + "," + defstr+str[k];
		}
		return tmp; 
	}			
	
	public static Integer inttoInteger(int i) { 
		Integer rtnInteger = new Integer(i);
		return rtnInteger;
	}
	
}
