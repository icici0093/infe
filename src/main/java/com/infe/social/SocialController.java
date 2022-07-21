package com.infe.social;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SocialController {
	
	@Autowired
	private SocialService sService;

	@RequestMapping(value="/social/replyList", method=RequestMethod.GET)
	public String boardList(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		return sService.socialList(request, response, model, redirectAttributes);
	}
	
	@ResponseBody
	@RequestMapping(value = "/social/setReply", method = RequestMethod.POST)
	public Map<String, Object> setReply(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		return sService.setReply(request, response);
	}
	
	@RequestMapping(value = "/social/movView", method = RequestMethod.GET)
	public String movView(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		return sService.movView(request, response, model, redirectAttributes);
	}
}
