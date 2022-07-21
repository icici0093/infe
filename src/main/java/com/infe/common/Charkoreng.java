package com.infe.common;

import java.io.UnsupportedEncodingException;

public final class Charkoreng {
	
	/**
	 * 8859_1 => euc-kr
	 * 
	 * @param english 변환할 문자열
	 * @return 변환된 문자열
	 */
	public static synchronized String fnEng2Kor(String english) {
		String korean = null;
		if (english == null) return null;
		try { korean = new String(new String(english.getBytes("8859_1"), "euc-kr")); }
		catch (UnsupportedEncodingException e) { korean = new String(english); }
		return korean;
	}

	/**
	 * euc-kr => 8859_1
	 * 
	 * @param korean 변환할 문자열
	 * @return 변환된 문자열
	 */
	public static synchronized String fnKor2Eng(String korean) {
		String english = null;
		if (korean == null) return null;
		english = new String(korean);
		try { english = new String(new String(korean.getBytes("euc-kr"), "8859_1")); }
		catch (UnsupportedEncodingException e) { english = new String(korean); }
		return english;
	}
}
