package com.infe.member;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	
	@Autowired
	MemberService mService;
	
	// 로그인 view 접근
	@RequestMapping(value = "/loginView", method={RequestMethod.GET,RequestMethod.POST})
	public String memberLoginView() {
		return "member/login";
	}
	
	// 로그인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String memberLogin(HttpServletRequest request, @ModelAttribute Member member, Model model) {
		Member mOne = new Member(member.getMemID(), member.getMemPW());
		Member loginUser = mService.loginMember(mOne);
		
		if(loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			return "redirect:/"; 
		}else {
			model.addAttribute("msg", "로그인 실패!!");
			return "common/errorPage";
		}
	}
	
	// 로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String memberLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	// 회원가입 view 접근
	@RequestMapping(value="/signUpView", method={RequestMethod.GET,RequestMethod.POST})
	public String memberSignUpView() {
		return "member/signUp";
	}
	
	// 회원가입
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public String memberEnroll(@ModelAttribute Member member, Model model) {
		int result = mService.enrollMember(member); 
		if(result > 0) {
			return "redirect:/";
		}else {
			model.addAttribute("msg", "회원가입 실패!");
			return "common/errorPage";
		}
	}
	
	// 아이디, 비밀번호 찾기 view 접근
	@RequestMapping("/findIdpw")
	public String memberFindIdpwView() {
		return "member/findIdpw";
	}
	
	// 마이페이지
	@RequestMapping(value = "/mypage", method={RequestMethod.GET,RequestMethod.POST})
	public String myPage() {
		return "member/myPage";
	}
}
