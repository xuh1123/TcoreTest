package com.tcore.crudTest.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcore.crudTest.entities.Employee;
import com.tcore.crudTest.service.EmpService;

@Controller
public class TestController {
	@Autowired
	EmpService empService;
	
	
	//发送方请求到testlist页面
	@RequestMapping("/totest")
	public String toTestList(Model model) {
		Collection<Employee> emps = empService.findAll();
		model.addAttribute("emps",emps);
		return "/emps/testlist";
	}

	// 处理ajax请求到test页面
	@ResponseBody
	@RequestMapping("/totest/{page}")
	public Map<String, Object> toTestList(@PathVariable("page") Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 每页设置为n条记录
			List<Employee> list = empService.findByLimit(page, 5);
			// 根据每页展示记录数确定总页数
			int totalpages = empService.getTotalpages(5);// 参数pageSize

			System.out.println(list);
			map.put("list", list);
			map.put("totalpages", totalpages);
			System.out.println(totalpages);
			// return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
