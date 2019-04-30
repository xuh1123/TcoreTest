//package com.tcore.crudTest.config;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.tcore.crudTest.interceptor.LoginHandlerInterceptor;
//
//@Configuration
//public class MyMvcConfig implements WebMvcConfigurer{
//
//	//拦截器
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// TODO Auto-generated method stub
//		//将自定义拦截器加入组件,拦截所有请求
//		registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//		.excludePathPatterns("/user/login","/login");
//	}
//	
//		
//}
