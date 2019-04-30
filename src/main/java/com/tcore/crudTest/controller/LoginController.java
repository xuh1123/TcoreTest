package com.tcore.crudTest.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import com.sun.media.jfxmedia.logging.Logger;
import com.tcore.crudTest.util.CookiesUtil;

@Controller
public class LoginController {
	
	//到登陆页面
	@RequestMapping("/tologin")
	public String login() {
		System.out.println("tologin请求进来了");
		return "login";
	}
	
	//简单验证，密码为123456的可以登录
/*	@RequestMapping("/user/login")
	public String toLogin(String username,String password,HttpSession session,Map<String, Object> map,HttpServletResponse response,HttpServletRequest request) {
		//如果是第一次登录
		//登录成功，将用户信息保存到session中
		if(!StringUtils.isEmpty(username)&&password.equals("123456")) {
			session.setAttribute("loginuser", username);
			//获取自动登录的checkbox值并用来判断
			String auto = 	request.getParameter("auto");
			System.out.println("auto====="+auto);
			//如果用户勾选了自动登录,将用户信息添加到cookie中
            if(auto!=null) {
            	//对中文张三进行编码（cookie不能存储中文）
				try {
					String username_code = URLEncoder.encode(username, "UTF-8");
					Cookie cookie_username = new Cookie("cookie_username",username_code);//创建cookie
					Cookie cookie_password = new Cookie("cookie_password",password);
					//设置cookie的持久化时间
					cookie_username.setMaxAge(60*60);//单位秒
					cookie_password.setMaxAge(60*60);
					//设置cookie的携带路径
					cookie_username.setPath(request.getContextPath());//设置为当前项目下都携带这个cookie
					cookie_password.setPath(request.getContextPath());
					//发送cookie
					response.addCookie(cookie_username);//向客户端发送cookie
					response.addCookie(cookie_password);
					Cookie[] cookies =	request.getCookies();
					for(Cookie cookie:cookies) {
						System.out.println(cookie.getName());	
						System.out.println(cookie.getValue());	
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
			return "redirect:/emps";
			//判断是否是退出权限之后重定向到此页面来的。
		}else if(request.getAttribute("msg")!=null) {
			return "login";
		}
		//登录失败
		map.put("msg", "密码错误");
		return "login";
	}*/
	
	@RequestMapping("/user/login")
	public String Login(String username,String password,Model model,Map<String,String> map,HttpSession session,Boolean rememberMe) {
		System.out.println("login请求进来了。。。。。。");
		System.out.println(username);
		System.out.println("rememberme========"+rememberMe);
		if(username==null&&password==null) {
			map.put("msg", "请输入用户名和密码");
			return "login";
		}
		
		//  使用shiro进行登录验证
		//	获取subject，是一个对于你当前所需要设计的项目保护的一个抽象概念。
		Subject subject = SecurityUtils.getSubject();
		session.setAttribute("loginuser", username);
		//封装用户数据，令牌。
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		rememberMe = rememberMe==null? false:rememberMe;
		token.setRememberMe(rememberMe);
		
		try {
			//执行登录方法
			subject.login(token);
			//成功登录
			return "redirect:/main/tomain";
		} catch (UnknownAccountException e) {
			//登录失败，用户名不存在
		//	model.addAttribute("msg","用户名不存在");
			map.put("msg","用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException e) {
			//登录失败，密码错误
		//	model.addAttribute("msg", "密码错误");
			map.put("msg", "密码错误");
			return "login";
		}
	}
	
	@RequestMapping("/main/tomain")
	public String toMain() {
//		return "redirect:/emps";
		return "dashboard";
	}
	
	//退出，清空session，清空cookie并重定向到登陆页面
	@RequestMapping("/loginout")
	public String loginOut(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
				//清空前台记住我功能设置的cookie的值
				Cookie cookie_username = new Cookie("username","");
				Cookie cookie_password = new Cookie("password",""); 
				cookie_username.setMaxAge(0);
				cookie_password.setMaxAge(0);
				cookie_username.setPath("/");
				cookie_password.setPath("/");
				response.addCookie(cookie_username);
				response.addCookie(cookie_password);
				session.setAttribute("loginuser", null);
		Subject subject  =SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/tologin";
	}
	
	@RequestMapping("/noAuth")
	public String noAuth(){
		return "noAuth";
	}
}
