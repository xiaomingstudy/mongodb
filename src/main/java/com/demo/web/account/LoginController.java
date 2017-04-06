/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.demo.web.account;

import com.demo.entity.RoleButtonMsg;
import com.demo.entity.User;
import com.demo.service.account.AccountService;
import com.demo.service.account.RoleButtonMsgServer;
import com.demo.service.account.ShiroDbRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private RoleButtonMsgServer roleButtonMsgServer;

	@RequestMapping(method = RequestMethod.GET)
	public String login() {

		return "account/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "account/login";
	}
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView indexForm(Model model) {
		ModelAndView modelAndView = new ModelAndView("account/index");
		ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Long userId = user.id;
		User thisUser = accountService.getUser(userId);
		List<RoleButtonMsg> roleButtonMsg =roleButtonMsgServer.getRoleButtonMsg(Long.valueOf(thisUser.getRoleId()));

		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("roleButtonMsg",roleButtonMsg);
		Integer power ;
		if(thisUser.getRoleId()==1){
			power=1;
			session.setAttribute("rolePower",1);
		}else if(thisUser.getRoleId()==2){
			power=2;
			session.setAttribute("rolePower",2);
		}else{
			power=3;
			session.setAttribute("rolePower",3);
		}
		modelAndView.addObject("power",power);
		//return "account/index";
		return modelAndView;
	}



}
