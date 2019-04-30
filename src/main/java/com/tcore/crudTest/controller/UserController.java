package com.tcore.crudTest.controller;

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
import com.tcore.crudTest.entities.User;
import com.tcore.crudTest.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	// 到用户列表页面
	@RequestMapping("/userlist")
	public String empList(Model model) {
		List<User> userlist = userService.getAll();
		model.addAttribute("userlist", userlist);
		/*for (User user : userlist) {
			System.out.println(user.getId());

		}*/
		return "user/userlist";
	}
	
	//用户页面加载完毕以后前台发ajax请求，进行分页展示
	@ResponseBody
	@RequestMapping("/userlist/{page}")
	public Map<String, Object> toTestList(@PathVariable("page") Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 每页设置为n条记录
			List<User> list = userService.findByLimit(page, 5);
			// 根据每页展示记录数确定总页数
			int totalpages = userService.getTotalpages(5);// 参数pageSize

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

	// 点击添加按钮到用户添加页面
	@RequestMapping("/toadduser")
	public String toAddEmp() {

		return "user/adduser";
	}

	// 点击提交，进行数据添加，并重定向到列表页面
	@RequestMapping("/adduser")
	public String addEmp(User user) {
		System.out.println("测试有没有接收到数据。。。。" + user);
		userService.addUser(user);
		return "redirect:/userlist";
	}

	// 点击编辑到修改页面(实际是到添加页面，回显修改员工的本条数据)
	@RequestMapping("/toadduser/{id}")
	public String updateEmp(@PathVariable("id") Integer id, Model model) {
		// 根据员工id，查出员工信息，回显到员工修改页面
		User user = userService.findUserById(id);
		model.addAttribute(user);
		System.out.println(user);
		return "user/adduser";
	}
	
	//点击修改进行数据更新并且重新定位到列表页面
		@PutMapping("/adduser")
		public String updateEmp(User user) {
			System.out.println("查看修改后的员工数据....."+user);
			userService.updateUser(user);
			return "redirect:/userlist";
		}
		
		//点击删除，对记录进行删除,重定向到列表页面
		@RequestMapping("/deluser/{id}")
		public String deleteEmp(@PathVariable("id")	Integer id) {
			userService.deleteUser(id);
			return "redirect:/userlist";
			
		}
		
		//模糊查询，前台传来的默认值page第一页开始。
		@ResponseBody
		@RequestMapping("/searchUser/{info}")
		public	Map<String, Object> searchEmp(@PathVariable("info") String info,@RequestParam("page") Integer page) {
			Map<String, Object> map  =new HashMap<String,Object>();
			//对查询到的数据进行重新分页
			List<User> user =  userService.findSearchByLimit(info,page,5);
			// 根据每页展示记录数确定总页数
			try {
				int totalpages = userService.getSearchTotalpages(5,info);
				map.put("list", user);
				map.put("totalpages", totalpages);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 参数pageSize
				return map;
		}
}
