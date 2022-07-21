package com.infe.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.captcha.Captcha;

@Controller
public class CaptchaController {
	@RequestMapping("/captcha/index")
	public String captcha() throws Exception {
		return "/captcha/index";
	}

	@RequestMapping("/captcha/CaptChaImg")
	public void CaptChaImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 new CaptCha().getCaptCha(request, response); 
	}
	
	@ResponseBody
	@RequestMapping("/captcha/CaptchaSubmit")
	public String CaptchaSubmit(HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		System.out.println("CaptchaSubmit!!!!!");
		String rst = "";
		Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
		String answer = request.getParameter("answer"); //사용자가 입력한 문자열
		if ( answer != null && !"".equals(answer) ) {
			if (captcha.isCorrect(answer)) { //사용자가 입력한 문자열과 CaptCha 클래스가 생성한 문자열
				request.getSession().removeAttribute(Captcha.NAME);
				rst = "s";//"입력값이 일치 합니다.";
			} else {
				rst = "e";//"입력값이 일치 하지 않습니다.";
			}

		} 

		System.out.println("rst : " + rst);
		return rst;
	}

}
