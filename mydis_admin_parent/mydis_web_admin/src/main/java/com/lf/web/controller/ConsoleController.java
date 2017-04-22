package com.lf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConsoleController {
	
	/** 主页面 */
	@RequestMapping(value="/console/main")
	public String main(Model model){
		
		return "main";
	}
	

}
