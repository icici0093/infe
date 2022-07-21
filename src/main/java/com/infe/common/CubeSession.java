package com.infe.common;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CubeSession implements HttpSessionListener
{
	 public void sessionCreated(HttpSessionEvent se)
	 {
	  HttpSession session = se.getSession();
	  System.out.println("Create session : " + session.getId());
	 }
	 public void sessionDestroyed(HttpSessionEvent se) 
	 {
	  HttpSession session = se.getSession();
	  System.out.println("Close session : " + session.getId());
	  
	 }
}