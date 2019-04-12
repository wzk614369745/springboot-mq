package com.ahut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ahut.entity.User;
import com.ahut.mq.MsgProducer;
import com.ahut.mq.MsgReciver;
import com.ahut.service.UserService;
@Controller
@RequestMapping("/testBoot")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	MsgProducer msg;
	@Autowired
	MsgReciver reciver;
	@RequestMapping("/getUser/{a}")
	public User user(@PathVariable("a") int a){
		return userService.getUser(a);	
	}
	@RequestMapping("/toindex")
	public String toIdex(Model model){
		model.addAttribute("a", "a");
		return "index";
	}
	@RequestMapping("/insertUser")
	public String insertUser(){
		int insert = userService.insert();
		return "index";
	}
	@RequestMapping("/hello")
	public void hello(){
		msg.sendMsg("aaa");
	}
}
