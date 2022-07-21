package com.infe.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 날짜/시간 반환 클래스.
 * 
 * @author dolgamza
 *
 */

public final class DateTime {

	private static final Logger logger = LoggerFactory.getLogger(DateTime.class);
	
	private DateTime() {}
	
	public static void check(String s) throws Exception { DateTime.check(s, "yyyy-MM-dd"); } 

	
	public static void check(String s, String format) throws java.text.ParseException {
		if ( s == null ) { throw new NullPointerException("date string to check is null"); }
		if ( format == null ) { throw new NullPointerException("format string to check date is null"); }
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
		java.util.Date date = null;
		try { date = formatter.parse(s); }
		catch(java.text.ParseException e) { throw new java.text.ParseException(e.getMessage() + " with format \"" + format + "\"",e.getErrorOffset()); }
		if ( ! formatter.format(date).equals(s) ) { throw new java.text.ParseException("Out of bound date:\"" + s + "\" with format \"" + format + "\"",0); }
	}

	public static String getDateString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("yyyy-MM-dd", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	public static int getDay() { return getNumberByPattern("dd"); }

	public static String getFormatString(String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat (pattern, java.util.Locale.KOREA);
		String dateString = formatter.format(new java.util.Date());
		return dateString;
	}

	public static int getMonth() { return getNumberByPattern("MM"); }
	public static int getHour() { return getNumberByPattern("H"); }
	public static int getMin() { return getNumberByPattern("m"); }
	public static int getSec() { return getNumberByPattern("s"); }
	

	public static int getNumberByPattern(String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat (pattern, java.util.Locale.KOREA);
		String dateString = formatter.format(new java.util.Date());
		return Integer.parseInt(dateString);
	}

	public static String getShortDateString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("yyyyMMdd", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}
	
	public static String getShort_DateString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("yyyy-MM-dd", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}	

	public static String getShortTimeString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("HHmmss", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	public static String getTimeStampString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("yyyy-MM-dd HH:mm:ss", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}
	
	public static String getTimeStampString2() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("yyyyMMddHHmmss", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}	
	
	public static String getTimeStamptoString(String stempStr) {
		java.text.SimpleDateFormat sdfCurrent = new java.text.SimpleDateFormat ("yyyyMMdd", java.util.Locale.KOREA); 
		Timestamp currentTime = new Timestamp(Long.parseLong(stempStr)); 		
		String strDate= new String(sdfCurrent.format(new java.sql.Date(currentTime.getTime())));  
		return strDate;
	}	

	public static String getTimeString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("HH:mm:ss", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}
	


	public static int getYear() {
		return getNumberByPattern("yyyy");
	}
	
	public static boolean getNewChk(String DateStr, int NewDate) {
		boolean ren_chk = false;
		
		String nowdate = getShortDateString();
		String chkdate = getAddTime(nowdate, NewDate);
		chkdate = chkdate.replaceAll("/","");
		DateStr = DateStr.substring(0, 10).replaceAll("-", "");
		if (Integer.parseInt(chkdate) <= Integer.parseInt(DateStr)) ren_chk = true;
		return ren_chk;
	}	
	
	/**
	 * <pre>
	 *   날짜 문자열을 원하는 형식으로 변환하여 돌려준다.
	 * </pre>
	 * 
	 * By C.S, JEON
	 * 
	 * @param strSourceDataFormat 원본 날짜 형식
	 * @param strTargetDataFormat 목표 날짜 형식
	 * @param strSourceDate 변환할 날짜 문자열
	 * @return 목표형식의 날짜 문자열
	 */
	public static String repDateFormat(String strSourceDataFormat, String strTargetDataFormat, String strSourceDate) {
		if (CubeUtils.nvl(strSourceDate).equals("")) {
			return "";
		}
		java.text.DateFormat sourceDF = new java.text.SimpleDateFormat(strSourceDataFormat, java.util.Locale.KOREAN); 
		java.text.DateFormat targetDF = new java.text.SimpleDateFormat(strTargetDataFormat, java.util.Locale.KOREAN);
		String strTargetDate = null;
		try { strTargetDate = targetDF.format(sourceDF.parse(strSourceDate)); }
		catch (java.text.ParseException e) { logger.debug(e.getMessage()); }
		return strTargetDate;
	}
	
	public static String repDateFormatEng(String strSourceDataFormat, String strTargetDataFormat, String strSourceDate) {
		if (CubeUtils.nvl(strSourceDate).equals("")) {
			return "";
		}
		java.text.DateFormat sourceDF = new java.text.SimpleDateFormat(strSourceDataFormat, java.util.Locale.KOREAN); 
		java.text.DateFormat targetDF = new java.text.SimpleDateFormat(strTargetDataFormat, java.util.Locale.US);
		String strTargetDate = null;
		try { strTargetDate = targetDF.format(sourceDF.parse(strSourceDate)); }
		catch (java.text.ParseException e) { logger.debug(e.getMessage()); }
		return strTargetDate;
	}
	
	/**
	 * <pre>
	 *   두 날짜 문자열을 비교하여 차이를 분으로 리턴.
	 * </pre>
	 * 
	 * By C.S, JEON
	 * 
	 * @param d1 날짜 문자열 1
	 * @param d2 날짜 문자열 2
	 * @return - : d2가 크다, + : d1이 크다, 0 : 같다.
	 */
	public static long cmpDate(String d1, String d2) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parseD1 = null;
		java.util.Date parseD2 = null;
		try{parseD1 = formatter.parse(d1);
			parseD2 = formatter.parse(d2); }
		catch (java.text.ParseException e) { logger.debug(e.getMessage()); }
	    return (parseD1.getTime() - parseD2.getTime()) / (60 * 1000 * 60 * 24);
	}
	
	/**
	 * 지정한 날짜로부터 지정한 일 수만큼 떨어져 있는 날짜 구하기
	 * 
	 * @param yyyyMMdd	날짜 String
	 * @param interval	날짜로부터의 간격
	 * @return			날짜 String
	 */
	public static String getAddTime(String yyyyMMdd, int interval){

		 java.util.Calendar calendar = java.util.Calendar.getInstance();
		  
		 calendar.set(Integer.parseInt(yyyyMMdd.substring(0,4)), Integer.parseInt(yyyyMMdd.substring(4,6))-1, Integer.parseInt(yyyyMMdd.substring(6)));
		 calendar.set(java.util.Calendar.DAY_OF_YEAR, calendar.get(java.util.Calendar.DAY_OF_YEAR)+interval);
		 
		 String year = calendar.get(java.util.Calendar.YEAR)+"";
		 String month = ( (calendar.get(java.util.Calendar.MONTH)+1)+"" ).length()==1?"0"+(calendar.get(java.util.Calendar.MONTH)+1):(calendar.get(java.util.Calendar.MONTH)+1)+"";
		 String day = ((calendar.get(java.util.Calendar.DAY_OF_MONTH)+"").length()==1?"0"+calendar.get(java.util.Calendar.DAY_OF_MONTH):""+calendar.get(java.util.Calendar.DAY_OF_MONTH));

		 String rday = year+"/"+month+"/"+day;

		 return rday;
	}
	
	/**
	 * 지정한 날짜로부터 지정한 일 수만큼 떨어져 있는 날짜 구하기
	 * 
	 * @param yyyyMMdd	날짜 String
	 * @param interval	날짜로부터의 간격
	 * @return			날짜 String
	 */
	public static String getDiffDay(String yyyyMMdd, int interval){

		 java.util.Calendar calendar = java.util.Calendar.getInstance();
		  
		 calendar.set(Integer.parseInt(yyyyMMdd.substring(0,4)), Integer.parseInt(yyyyMMdd.substring(4,6))-1, Integer.parseInt(yyyyMMdd.substring(6)));
		 calendar.set(java.util.Calendar.DAY_OF_YEAR, calendar.get(java.util.Calendar.DAY_OF_YEAR)+interval);
		 
		 String year = calendar.get(java.util.Calendar.YEAR)+"";
		 String month = ( (calendar.get(java.util.Calendar.MONTH)+1)+"" ).length()==1?"0"+(calendar.get(java.util.Calendar.MONTH)+1):(calendar.get(java.util.Calendar.MONTH)+1)+"";
		 String rday = ((calendar.get(java.util.Calendar.DAY_OF_MONTH)+"").length()==1?"0"+calendar.get(java.util.Calendar.DAY_OF_MONTH):""+calendar.get(java.util.Calendar.DAY_OF_MONTH));
		 String sdiff = year + month + rday;
		 return sdiff;
	}	 
	 
	/**
	 * 지정한 날짜로부터 지정한 월 수만큼 떨어져 있는 날짜 구하기
	 * 
	 * @param yyyyMM	날짜 String
	 * @param interval	날짜로부터의 간격
	 * @return			날짜 String
	 */
	public static String getDiffMonth(String yyyyMM, int interval){

		yyyyMM = yyyyMM + "15";
		yyyyMM = getDiffDay(yyyyMM,-30);
		yyyyMM = yyyyMM.substring(0, 6);
		return yyyyMM;
	}		
	
	
	 /**
     * <pre>
     * 해당월의 일자수를 리턴한다.
     * </pre>
     *
     * @param int year
     * @param int month
     *
     * @return int
     */
    public static int getDayCount(int year, int month)
    {
        int day[] = {31,28,31,30,31,30,31,31,30,31,30,31};

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            day[1] = 29;
        }
        return day[month-1];
    }
	
	public static String dateFilter(String src, String format){
		return dateFilter(src, format, true);
	}
	
	public static String dateFilter(String src, String format, boolean zeroFG){
		return dateFilter(src, format, zeroFG, "F");
	}

    /**
    * 전달된 String ('YYYYMMDDHH24MISS')을  formate에 맞게 변환
    * YYYY는 1900으로 MM은 12등으로 변환.
    * 예) src : 20011213134532
    *      format : "YYYY년 MM월 DD일 HH24:MI:SS"
    *   -> 2001년 12월 13일 13:45:32
    *
    * @author 민선기(update 이시형 - 2003/06/02)
    * @param str           변환시킬 문자열
    * @param format     변환형식
	* @param zeroFG       (월,일,시,분 중)한자리일때 앞에 0을 붙일지 여부(true: 붙임, false:않붙임)
    * @param scan         F:날짜시간, D: 날짜만
    * @return 변환된 문자열
    */
    public static String dateFilter(String src, String format, boolean zeroFG, String scan)
    {
        if ( src == null || src.length() <14 || (format.length() < 2) )
            return "";

        String str_ret = "";
        int iyyyy, iyy, imm, idd, i24hh, ihh, imi, iss,temphh;
		String strhh = "";

		if(zeroFG) {     //한자리 일때 0이 붙임
			temphh = Integer.parseInt(src.substring(8,10));		//시간(작업용)

			if( temphh > 12 ) ihh = temphh-12;
			else ihh = temphh;

			strhh = intToString(ihh);
			if(strhh.length() <= 1) strhh =  "0" + strhh;

		    str_ret = replaceString(format, "YYYY", src.substring(0,4));
	        str_ret = replaceString(str_ret, "YY", src.substring(2,4));
	        str_ret = replaceString(str_ret, "MM", src.substring(4,6));
	        str_ret = replaceString(str_ret, "DD", src.substring(6,8));
	        if(!"D".equals(scan)){
		        str_ret = replaceString(str_ret, "HH24", src.substring(8,10));
		        str_ret = replaceString(str_ret, "HH", strhh);
		        str_ret = replaceString(str_ret, "MI", src.substring(10,12));
		        str_ret = replaceString(str_ret, "SS", src.substring(12,14));
	        }
		} else {		//한자리 일때 0 붙이지 않음

			iyyyy = Integer.parseInt(src.substring(0,4));			//년
			iyy = Integer.parseInt(src.substring(2,4));				//년(두자리)
			imm = Integer.parseInt(src.substring(4,6));			//월
			idd = Integer.parseInt(src.substring(6,8));			//일
			i24hh = Integer.parseInt(src.substring(8,10));		//시간

			if( i24hh > 12 ) ihh = i24hh-12;
			else ihh = i24hh;

			imi = Integer.parseInt(src.substring(10,12));			//분
			iss = Integer.parseInt(src.substring(12,14));			//초

	        str_ret = replaceString(format, "YYYY", intToString(iyyyy));
	        str_ret = replaceString(str_ret, "YY", intToString(iyy));
	        str_ret = replaceString(str_ret, "MM", intToString(imm));
	        str_ret = replaceString(str_ret, "DD", intToString(idd));
	        if(!"D".equals(scan)){
		        str_ret = replaceString(str_ret, "HH24", intToString(i24hh));
			    str_ret = replaceString(str_ret, "HH", intToString(ihh));
		        str_ret = replaceString(str_ret, "MI", intToString(imi));
		        str_ret = replaceString(str_ret, "SS", intToString(iss));
	        }
		}
		return str_ret;
    }

    private static String replaceString(String src, String from, String to)
    {
        StringBuffer res_Buff = new StringBuffer();
        int pos=-1;

        if (src == null || from == null || from.equals("")) return src;
        if (to == null) to = "";

        while (true)
        {
            if ((pos = src.indexOf(from)) == -1)
            {
                res_Buff.append(src);
                break;
            }
            res_Buff.append(src.substring(0, pos)).append(to);
            src = src.substring(pos + from.length());
        }
        return res_Buff.toString();
    }
    
    private static String intToString(int i)
    {
        return (new Integer(i)).toString();
    }
	
	/**
	 * 인자로 전달된 시각 <code>s</code> 에서  특정 시간(time)을  더한 시각을  인자로 전달된 <code>format</code> 형식으로 표현하는 문자열을 리턴한다. 
 * <p>2005년 2월 25일에서 일주일(7일) 후의 날짜를 표현하는 문자열 얻어오는  코드 사용예:
	 * 	<p><blockquote><pre>
	 * String dateString=DateTimeUtil.addDays("20050225",7,"yyyyMMdd");
	 * </pre></blockquote>  
	 * 	@param s 기준 날짜
	 * @param time 더할 시간
	 * @param format 날짜 표현 포멧 
	 * @return 더해진 시각을 표현하는 문자열
	 * @exception java.text.ParseException 잘못된 날짜이거나. 날짜를 표현하는 문자열이 <code>format</code> 형식에 맞지 않는 경우.
	 */
	public static String addTimes(String s, int time, String format)
		throws java.text.ParseException {
		java.text.SimpleDateFormat formatter =
			new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = check2(s, format);

		date.setTime(date.getTime() + ((long)  1000 * 60 * 60 * time));
		return formatter.format(date);
	}
	
	/**
	 * check date string validation with an user defined format.
	 * @param s date string you want to check.
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return date java.util.Date
	 * @exception java.text.ParseException 잘못된 날짜이거나. 날짜를 표현하는 문자열이 format 에 맞지 않는 경우 발생
	 */
	public static java.util.Date check2(String s, String format)
		throws java.text.ParseException {
		if (s == null)
			throw new java.text.ParseException(
				"date string to check is null",
				0);
		if (format == null)
			throw new java.text.ParseException(
				"format string to check date is null",
				0);

		java.text.SimpleDateFormat formatter =
			new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = null;
		try {
			date = formatter.parse(s);
		} catch (java.text.ParseException e) {
			/*
			throw new java.text.ParseException(
				e.getMessage() + " with format \"" + format + "\"",
				e.getErrorOffset()
			);
			*/
			throw new java.text.ParseException(
				" wrong date:\"" + s + "\" with format \"" + format + "\"",
				0);
		}

		if (!formatter.format(date).equals(s))
			throw new java.text.ParseException(
				"Out of bound date:\""
					+ s
					+ "\" with format \""
					+ format
					+ "\"",
				0);
		return date;
	}
	
	/**
	 * 현재시각 에서  특정 시간(time)을  더한 시각을  인자로 전달된 <code>format</code> 형식으로 표현하는 문자열을 리턴한다. 
 * <p>현재시각에서 23시간 후를 표현하는 문자열 얻어오는  코드 사용예:
	 * 	<p><blockquote><pre>
	 * String dateString=DateTimeUtil.addDays(23,"yyyy/MM/dd hh:mm");
	 * </pre></blockquote>  
	 * @param time 더할 시간
	 * @param format 시각 표현 포멧 
	 * @return 더해진 시각을 표현하는 문자열
	 * @exception java.text.ParseException 잘못된 날짜이거나. 날짜를 표현하는 문자열이 <code>format</code> 형식에 맞지 않는 경우.
	 */
	public static String addTimes( int time, String format)
		throws java.text.ParseException {
	String fomatted=		getFormatString(format);
		return addTimes(fomatted,time,format);
	}

	
	
	  public static long diffOfDate(String begin, String end) throws Exception
	  {
		  java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
	 
		  java.util.Date beginDate = formatter.parse(begin);
		  java.util.Date endDate = formatter.parse(end);
	 
	    long diff = endDate.getTime() - beginDate.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	 
	    return diffDays;
	  }	
	  
	  public static boolean diffDays( String param_date ) throws Exception{
			
			boolean rtn_flag = false;
			
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");

			String today = DateTime.getTimeStampString2();
			Date beginDate = formatter.parse(param_date);
		    Date endDate = formatter.parse(today);
		    
		    long diff = beginDate.getTime() - endDate.getTime();
		    long diffDays = diff / (1000);
			
		    if(diffDays >= 0) rtn_flag = true;
		    
		    return rtn_flag;
		}
	
	
	/**
	 * 	현재날짜를 기준으로 날짜요소를 가감한다.<p>
	 *
	 *  @param	flag <code>날짜요소(<font color=blue>year</font> : 년도, <font color=red>month</font> : 월, <font color=green>day</font> : 일)</code><br>
	 *  @param	value <code>변경 시킬 값</code><br>
	 *  @return	<code>입력 받은 날짜요소에 해당하는 값을 가감한 날짜 문자열</code>
	 *	@author	pronema
	 */
	public static String getDateCalculated(String flag, int value)
	{
		String result = getDateCalculated(getShortDateString(), flag, value);

		return result;
	}
	
	/**
	 * 	입력받은 날짜를 년,월,일로 파싱해 정수형으로 변경한 후 리턴한다.<p>
	 *
	 *  @param	dateStr <code>입력 날짜</code><br>
	 *  @return	<code>생성된 int형 1차원 배열(각 필드순으로 년, 월, 일)</code>
	 *	@author	pronema
	 */
	public static int[] makeDateFactor(String dateStr)
	{
		int result[] = null;
		
		//String tempStr = "";

		if(dateStr != null && !dateStr.equals("") && dateStr.length() == 8)
		{	
			result = new int[3];

			result[0] = Integer.parseInt(dateStr.substring(0, 4));
			result[1] = Integer.parseInt(dateStr.substring(4, 6));
			result[2] = Integer.parseInt(dateStr.substring(6, 8));
		}
	
		return result;
	}
	
	/**
	 * 	입력받은 날짜를 기준으로 날짜요소를 가감한다.<p>
	 *
	 *  @param	startDateStr <code>기준 일자<br>
	 *  @param	flag <code>날짜요소(<font color=blue>year</font> : 년도, <font color=red>month</font> : 월, <font color=green>day</font> : 일)</code><br>
	 *  @param	value <code>변경 시킬 값</code><br>
	 *  @return	<code>입력 받은 날짜요소에 해당하는 값을 가감한 날짜 문자열</code>
	 *	@author	pronema
	 */
	public static String getDateCalculated(String startDateStr, String flag, int value)
	{
		String result = "";

		flag = flag.toUpperCase();
		
		Calendar cal = Calendar.getInstance(Locale.getDefault());

		int nowDateFactor[] = makeDateFactor(startDateStr);

		cal.set(nowDateFactor[0], nowDateFactor[1]-1, nowDateFactor[2]);

		if(flag.equals("YEAR"))
			cal.add(Calendar.YEAR, value);
		else if(flag.equals("MONTH"))
			cal.add(Calendar.MONTH, value);
		else if(flag.equals("DAY"))
			cal.add(Calendar.DATE, value);
		
		String year  = "" + cal.get(Calendar.YEAR);
		String month = "" + (cal.get(Calendar.MONTH) + 1);
		String day   = "" + cal.get(Calendar.DATE);

		if(month.length() == 1)
			month = "0" + month;
		
		if(day.length() == 1)
			day = "0" + day;
		
		result = year + month + day;

// System.out.println(nowDateFactor[0] + ", " + (nowDateFactor[1]-1) + ", " + nowDateFactor[2] + ", " + value + " = " + result);

		return result;		
	}
	// 날짜 관련된 옵션 태그 만들기

	public static String getDateTimeOptionString(String kind)
	{
		return getDateTimeOptionString(kind, "");
	}

	public static String getDateTimeOptionString(String kind, String yearKind)
	{
		String result = "";

		int startPos = -4;
		int endPos	 = 5;


		if(kind.equalsIgnoreCase("year"))
		{
			if(yearKind.equals("prev"))
			{
				startPos = -70;
				endPos = 0;
			}
			else if(yearKind.equals("next"))
			{
				startPos = 0;
				endPos = 10;
			}

			startPos = Integer.parseInt(getDateCalculated("YEAR", startPos).substring(0, 4));
			endPos = Integer.parseInt(getDateCalculated("YEAR", endPos).substring(0, 4));
		}
		else if(kind.equalsIgnoreCase("month"))
		{
			startPos = 1;
			endPos	 = 12;
		}
		else if(kind.equalsIgnoreCase("day"))
		{
			startPos = 1;
			endPos	 = 31;
		}
		else if(kind.equalsIgnoreCase("hour"))
		{
			startPos = 0;
			endPos	 = 24;
		}
		else if(kind.equalsIgnoreCase("minute"))
		{
			startPos = 0;
			endPos	 = 59;
		}
		else if(kind.equalsIgnoreCase("second"))
		{
			startPos = 0;
			endPos	 = 59;
		}

		for(int i=startPos; i<=endPos; i++)
		{
			if(i<10)
				result += "<option value=0" + i + ">0" + i + "</option>";	
			else
				result += "<option value=" + i + ">" + i + "</option>";	
		}
		
		return result;	
	}
	public static void main(String args[]) throws ParseException{
		
		System.out.println( (DateTime.dateFilter(DateTime.getShortDateString()+"000000", "YYYY-MM-DD", true, "D")) );
		
		System.out.println( (DateTime.dateFilter(DateTime.getDateCalculated("month", -1)+"000000", "YYYY-MM-DD", true, "D")) );
		

	}
	
	public static String getymdhms() {
		return getShortDateString() + getShortTimeString();
	}	
	
	/**
	 * 특정 날짜에 대하여 요일을 구함(일 ~ 토)
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String getDateDayofweek(String date) throws Exception {
	    String day = "" ;
	    if (date.length() > 10) {
	    	date = date.substring(0,10);
	    }
	    date = date.replaceAll("-", "");
	    date = date.replaceAll("[.]", "");
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd") ;
	    Date nDate = dateFormat.parse(date) ;
	    Calendar cal = Calendar.getInstance() ;
	    cal.setTime(nDate);
	    int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
	    switch(dayNum){
	        case 1:
	            day = "일";
	            break ;
	        case 2:
	            day = "월";
	            break ;
	        case 3:
	            day = "화";
	            break ;
	        case 4:
	            day = "수";
	            break ;
	        case 5:
	            day = "목";
	            break ;
	        case 6:
	            day = "금";
	            break ;
	        case 7:
	            day = "토";
	            break ;
	    }
	    return day ;
	}	
	
	
	public static String getDateDetail(String date) throws Exception {
	    String day = "" ;
	    if (date.length() > 10) {
	    	date = date.substring(0,10);
	    }
	    date = date.replaceAll("-", "");
	    date = date.replaceAll("[.]", "");
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd") ;
	    Date nDate = dateFormat.parse(date) ;
	    Calendar cal = Calendar.getInstance() ;
	    cal.setTime(nDate);
	    int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
	    switch(dayNum){
	        case 1:
	            day = "일";
	            break ;
	        case 2:
	            day = "월";
	            break ;
	        case 3:
	            day = "화";
	            break ;
	        case 4:
	            day = "수";
	            break ;
	        case 5:
	            day = "목";
	            break ;
	        case 6:
	            day = "금";
	            break ;
	        case 7:
	            day = "토";
	            break ;
	    }
	    return day ;
	}	
	

	public static String formatTimeString(String dateStr) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date tempDate = null;
		try{
			tempDate = transFormat.parse(dateStr);
		}
		catch (java.text.ParseException e) { logger.debug(e.getMessage()); }		
		
		
		int SEC = 60;
		int MIN = 60;
		int HOUR = 24;
		int DAY = 30;
		int MONTH = 12;

		long curTime = System.currentTimeMillis();
		long regTime = tempDate.getTime();
		long diffTime = (curTime - regTime) / 1000;

		String msg = null;
		if (diffTime < SEC) {
			// sec
			msg = "방금 전";
		} else if ((diffTime /= SEC) < MIN) {
			// min
			msg = diffTime + "분 전";
		} else if ((diffTime /= MIN) < HOUR) {
			// hour
			msg = (diffTime) + "시간 전";
		} else if ((diffTime /= HOUR) < DAY) {
			// day
			msg = (diffTime) + "일 전";
		} else if ((diffTime /= DAY) < MONTH) {
			// day
			msg = (diffTime) + "달 전";
		} else {
			msg = (diffTime) + "년 전";
		}

		return msg;
	}	
	
	
}