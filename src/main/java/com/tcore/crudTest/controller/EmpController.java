package com.tcore.crudTest.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcore.crudTest.entities.Employee;
import com.tcore.crudTest.service.EmpService;


@Controller
public class EmpController {
	@Autowired
	EmpService empService;
	
//	@Autowired
//	SQLManager sqlManager;0.
	
	//前往员工列表页面
//	@RequestMapping("/emps/{page}")
//	@ResponseBody
	@RequestMapping("/emps")
	public String toEmpList(/*@PathVariable("page") int page*/Model model) {
		Collection<Employee> emps = empService.findAll();
		for(Employee emp:emps) {
			System.out.println(emp);
		}
		model.addAttribute("emps",emps);
		//获取前台ajax传进来的page
	//	int page = Integer.parseInt(request.getParameter("page")); 

		
		
		return "emps/list";
	}
	
	/*@ResponseBody
	@RequestMapping("/emps/{page}")
	public Map<String, Object> loadEmpList(@PathVariable("page")Integer page){
				Map<String, Object> map = new HashMap<String, Object>();
		try {
			//每页两条记录
			List<Employee> list = empService.findByLimit(page, 2);
			//根据每页展示记录数确定总页数
			int totalpages = empService.getTotalpages(2);//参数pageSize
		
			System.out.println(list);
			map.put("list", list);
			map.put("totalpages", totalpages);
			System.out.println(totalpages);
		//	return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}*/
	
	//点击添加按钮到员工添加页面
	@RequestMapping("/toaddemp")
	public String toAddEmp() {
		
		return "emps/add";
	}
	
	//点击提交，进行数据添加，并重定向到列表页面
	@RequestMapping("/addemp")
	public String addEmp(Employee emp) {
		System.out.println("测试有没有接收到数据。。。。"+emp);
		empService.addEmp(emp);
//		sqlManager.insert(emp);
		return "redirect:/emps";
	}
	
	//点击编辑到修改页面(实际是到添加页面，回显修改员工的本条数据)
	@RequestMapping("/toaddemp/{id}")
	public String updateEmp(@PathVariable("id") Integer id,Model model) {
		//根据员工id，查出员工信息，回显到员工修改页面
		System.out.println("测试有没有进来。。。。。。。");
		Employee emp = 	empService.findById(id);
		model.addAttribute("emp",emp);
		return "emps/add";
	}
	
	//点击修改进行数据更新并且重新定位到列表页面
	@PutMapping("/addemp")
	public String updateEmp(Employee emp) {
		System.out.println("查看修改后的员工数据....."+emp);
		empService.updateEmp(emp);
		return "redirect:/emps";
	}
	
	//点击删除，对记录进行删除,重定向到列表页面
	@RequestMapping("/delemp/{id}")
	public String deleteEmp(@PathVariable("id")	Integer id) {
		empService.deleteEmp(id);
		return "redirect:/emps";
		
	}
	
	// 处理ajax请求到test页面
		@ResponseBody
		@RequestMapping("/emps/{page}")
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
	//跟去ajax前台的值进行模糊查询
/*	@ResponseBody
	@RequestMapping("/searchEmp/{info}")
	public	Map<String, Object> searchEmp(@PathVariable("info") String info) {
		Map<String, Object> map  =new HashMap<String,Object>();
		List<Employee>	emps =  empService.searchEmp(info);
		try {
			int totalpages = empService.getTotalpages(5);
			map.put("list", emps);
			map.put("totalpages", totalpages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return map;
	}*/
	
	//此controller是测试用的controller
	@ResponseBody
	@RequestMapping("/searchEmpTest/{info}")
	public	Map<String, Object> searchEmpTest(@PathVariable("info") String info,@RequestParam("page") Integer page,Model model) {
		Map<String, Object> map  =new HashMap<String,Object>();
		//对查询到的数据进行重新分页
		List<Employee> emps =  empService.findSearchByLimit(info,page,5);
		// 根据每页展示记录数确定总页数
		try {
			int totalpages = empService.getSearchTotalpages(5,info);
			map.put("list", emps);
			map.put("totalpages", totalpages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 参数pageSize
			return map;
	}
	
	//模糊查询，前台传来的默认值page第一页开始。
	@ResponseBody
	@RequestMapping("/searchEmp/{info}")
	public	Map<String, Object> searchEmp(@PathVariable("info") String info,@RequestParam("page") Integer page) {
		Map<String, Object> map  =new HashMap<String,Object>();
		//对查询到的数据进行重新分页
		List<Employee> emps =  empService.findSearchByLimit(info,page,5);
		// 根据每页展示记录数确定总页数
		try {
			int totalpages = empService.getSearchTotalpages(5,info);
			map.put("list", emps);
			map.put("totalpages", totalpages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 参数pageSize
			return map;
	}
}
