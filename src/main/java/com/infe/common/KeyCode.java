package com.infe.common;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * 파일 유틸
 * @author juhani
 *
 */
public class KeyCode {

		public static String getKeyProperties(String opt){
			URL url = Thread.currentThread().getContextClassLoader().getResource("keycode.properties");
			Properties props = new Properties(); 
			try {
				props.load(url.openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(opt.equalsIgnoreCase("KEYCODESET")){
				String rDomain = props.getProperty("KEYCODESET");
				return rDomain;
			}else{
				return "";
			}
		}
		
		
	}