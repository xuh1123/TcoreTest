package com.tcore.crudTest.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.tcore.crudTest.shiro.UserRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
			@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 添加Shiro内置过滤器
		/**
		 * Shiro内置过滤器，可以实现权限相关的拦截器 常用的过滤器： anon: 无需认证（登录）可以访问 authc: 必须认证才可以访问 user:
		 * 如果使用rememberMe的功能可以直接访问 perms： 该资源必须得到资源权限才可以访问 role: 该资源必须得到角色权限才可以访问
		 */
		Map<String, String> filterMap = new LinkedHashMap<String, String>();
//		HashMap<String, Filter> myFilters = new HashMap<>();
//		myFilters.put("authc", new RememberAuthenticationFilter());
//		shiroFilterFactoryBean.setFilters(myFilters);
		filterMap.put("/tologin", "anon");
		filterMap.put("/user/login", "anon");
		filterMap.put("/asserts/*", "anon");
		
		// 只有admin用户可以进行员工添加页面操作
		filterMap.put("/toaddemp", "perms[admin]");
		// 只有admin用户可以进行员工删除操作
		filterMap.put("/delemp/*", "perms[admin]");
		// 只有admin用户可以进行用户添加页面操作
	    filterMap.put("/toadduser", "perms[admin]");
		// 只有admin用户可以进行用户删除操作
		filterMap.put("/deluser/*", "perms[admin]");
		// 只有admin用户可以进行用户修改操作
		filterMap.put("/toadduser/*", "perms[admin]");
		filterMap.put("/emps", "user");
		filterMap.put("/loginout", "anon");
		//改成user是为了让rememberMe生效
		filterMap.put("/*", "authc");

		// 修改调整的登录页面
		shiroFilterFactoryBean.setLoginUrl("/tologin");

		// 设置未授权提示页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

		return shiroFilterFactoryBean;

	}

	/**
	 * 创建DefaultWebSecurityManager
	 *	securityManager主要负责是shiro的核心部分，用于安全认证与授权
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 关联realm
		securityManager.setRealm(userRealm);
		// 注入记住我管理器
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	/**
	 * 创建Realm
	 */
	@Bean(name = "userRealm")
	public UserRealm getRealm() {
		return new UserRealm();
	}

	/**
	 * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
	 */
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}

	@Bean
	public CookieRememberMeManager rememberMeManager() {
		// System.out.println("ShiroConfiguration.rememberMeManager()");
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		// rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
		return cookieRememberMeManager;
	}

	@Bean
	public SimpleCookie rememberMeCookie() {
		 //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
	    SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
	    //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
	    //setcookie()的第七个参数
	    //设为true后，只能通过http访问，javascript无法访问
	    //防止xss读取cookie
	    simpleCookie.setHttpOnly(true);
	    simpleCookie.setPath("/");
	    //<!-- 记住我cookie生效时间30天 ,单位秒;-->
	    simpleCookie.setMaxAge(2592000);
		return simpleCookie;
	}
	
	/**
	 * FormAuthenticationFilter 过滤器 过滤记住我
	 * @return
	 */
	@Bean
	public FormAuthenticationFilter formAuthenticationFilter(){
	    FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
	    //对应前端的checkbox的name = rememberMe
	    formAuthenticationFilter.setRememberMeParam("rememberMe");
	    return formAuthenticationFilter;
	}

}
