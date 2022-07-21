package com.infe.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;

import kr.cipher.seed.*;

public class EncDec {

	public static byte[] getKey() throws Exception {
		String getdetkey = StrUtil.stringToHex(KeyCode.getKeyProperties("KEYCODESET"));
		byte[] bytes = ByteUtils.toBytes(getdetkey, 16);
		return  bytes;
	}
	public static String encrypt(String ecString) {
		String rtnStr = "";
		try {
			rtnStr = Seed128Cipher.encrypt(ecString, getKey(), null);
			rtnStr = URLEncoder.encode(rtnStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		} catch (Exception e) { }
		
		return rtnStr;
	}

	public static String decrypt(String deString)  {
		String rtnStr = "";
		try {
			rtnStr = URLDecoder.decode(deString, "UTF-8");
			rtnStr = Seed128Cipher.decrypt(rtnStr, getKey(), null);
		} catch (UnsupportedEncodingException e) {
		} catch (Exception e) { }		
		return rtnStr;
	}	
	
	public static String encryptSha(String input) throws NoSuchAlgorithmException {

	    String output = "";
	    StringBuffer sb = new StringBuffer();
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(input.getBytes());
	    byte[] msgb = md.digest();
	    for (int i = 0; i < msgb.length; i++) {
	        byte temp = msgb[i];
	        String str = Integer.toHexString(temp & 0xFF);
	        while (str.length() < 2) {
	            str = "0" + str;
	        }
	        str = str.substring(str.length() - 2);
	        sb.append(str);
	    }
	    output = sb.toString();
	    return output;
	}		
	
	
}