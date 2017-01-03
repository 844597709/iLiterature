package com.swust.kelab.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swust.kelab.service.web.AuthorService;
import com.swust.kelab.web.json.JsonAndView;

@Controller
@RequestMapping("optimize")
public class OptimizeController {
	
	@Resource
	private AuthorService authorService;
	
	@RequestMapping("test")
	public JsonAndView test(){
		JsonAndView jv=new JsonAndView();
		jv.addData("result", authorService.countAreaInfo(null));
		return jv;
	}
}

