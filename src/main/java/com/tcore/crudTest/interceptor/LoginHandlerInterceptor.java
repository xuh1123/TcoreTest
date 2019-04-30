//package com.tcore.crudTest.interceptor;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//public class LoginHandlerInterceptor implements HandlerInterceptor {
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		// TODO Auto-generated method stub
//		//先判断session中是否有信息
//		Object user = request.getSession().getAttribute("loginuser");
//				if(user==null) {
//				//没有session，在获取cookie进行判断
//				Cookie[] cookies=request.getCookies();
//				if(cookies!=null) {
//					//有cookies，就获取cookie进行判断
//					//遍历cookies
//					for(Cookie cookie:cookies) {
//						if("cookie_username".equals(cookie.getName())){
//						//有用户的cookie
//							String value = cookie.getValue();
//						//将用户的信息保存到session中
//							request.getSession().setAttribute("username", value);
//							//放行
//							return true;
//						}
//					}
//				}
//				//未登录
//				//返回登陆页面
//				request.setAttribute("msg", "没有权限请登录");	
//				request.getRequestDispatcher("/user/login").forward(request, response);
//				return false;
//				}else {
//					//已登录，放行请求
//					return true;
//				}
//	}
//	
//}
