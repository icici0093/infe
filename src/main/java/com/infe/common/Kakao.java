package com.infe.common;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infe.common.dao.BaseDao;

@Service
public class Kakao {
	
	@Autowired
	private BaseDao basedao;
	
	
	private static String SENDER_KEY = "73786e786329c14297d26e13ae02b212ddc0fa56";
	
	public boolean sendKakap(Map<String,String> sendData) throws Exception {
		boolean rtnbool = false;
		String msgset = typetoMessage(sendData.get("msgtype"));
		msgset = CubeUtils.nvl(msgset);
		Iterator<String> keys = sendData.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			msgset = msgset.replaceAll("#@" + key.toLowerCase() + "@#", sendData.get(key));
		}		
		
		CubeMap param = new CubeMap(sendData);
		if (param.get("send_phone").equals("")) param.set("send_phone","0445504450"); 
		param.set("msg_body", msgset);
		param.set("sender_key", SENDER_KEY);
		param.set("template_code", typetotempno(param.get("msgtype")));
		
//		System.out.println("msgset : " + msgset);
//		System.out.println("SENDER_KEY : " + SENDER_KEY);
//		System.out.println("template_code : " + typetotempno(param.get("msgtype")));
//		System.out.println("dest_phone : " + param.get("dest_phone"));
		
		rtnbool = basedao._Insert("Re_Kakao.setKakao", param);
		return rtnbool;
	}
	
	
	public String typetotempno (String tempStr) {
		String rtnTempNo = "";
		tempStr = CubeUtils.nvl(tempStr);
		tempStr = tempStr.replaceAll(" ", "");
		tempStr = tempStr.toUpperCase();
		if (tempStr.equals("QNAANSWER")) {  // 답변등록
			rtnTempNo = "bizp_2022032411304618311482100";
		}
		else if (tempStr.equals("COUNSEL")) {  // 구매상담
			rtnTempNo = "bizp_2017110917364922574417697";
		}
		return rtnTempNo;
	}
	
	public String typetoMessage (String tempStr) {
		String sendMsg = "";
		tempStr = CubeUtils.nvl(tempStr);
		tempStr = tempStr.replaceAll(" ", "");
		tempStr = tempStr.toUpperCase();
		if (tempStr.equals("QNAANSWER")) {  // 시승신청이면
			sendMsg = sendMsg + "[KDI] Q&A 답변이 등록되었습니다"+"\n";
			sendMsg = sendMsg + ""+"\n";			
			sendMsg = sendMsg + "KDI 웹사이트에서 #@name@#님이 문의하신 Q&A에 답변이 등록되었습니다.";
		}
		else if (tempStr.equals("COUNSEL")) {  // 구매상담
			sendMsg = sendMsg + "[르노삼성자동차]"+"\n";			
			sendMsg = sendMsg + "#@username@#님의 구매상담 신청이 접수되었습니다."+"\n";
			sendMsg = sendMsg + "지정하신 영업거점에서 확인 후 빠른 시일 내에 연락드리겠습니다."+"\n";
			sendMsg = sendMsg + ""+"\n";
			sendMsg = sendMsg + "신청내역"+"\n";
			sendMsg = sendMsg + "· 상담모델 (#@carname@#)"+"\n";
			sendMsg = sendMsg + "· 상담거점 (#@asasname@# / #@asastel@#)";
		}
		return sendMsg;
	}
}
