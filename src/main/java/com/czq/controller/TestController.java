package com.czq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/index.page")
	public String test() {
		return "test";
	}
}
