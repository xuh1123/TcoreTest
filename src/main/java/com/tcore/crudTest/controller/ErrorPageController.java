package com.tcore.crudTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController {
	@RequestMapping("error-500")
	public String toPage500() {
		return "error/error-500";
	}
}
