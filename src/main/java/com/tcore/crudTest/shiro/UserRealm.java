package com.tcore.crudTest.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tcore.crudTest.entities.User;
import com.tcore.crudTest.service.UserService;

public class UserRealm extends AuthorizingRealm{
	@Autowired
	UserService userservice;

	/**
	 * 执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//		System.out.println("执行授权逻辑");
		//获取登录的用户名
		String username = (String) principalCollection.getPrimaryPrincipal();
		System.out.println(username);
		//根据使用户名称获取对应的角色权限名称
		String rolename	=	userservice.getRoleNameByUserName(username);
		//给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermission(rolename);
		return info;
	}

	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		if(authenticationToken.getPrincipal()==null) {
			return null;
		}
//		System.out.println("认证逻辑执行了。。。。。。。");
		//判断用户名
		String username = authenticationToken.getPrincipal().toString();
		System.out.println(username);
		User user=	userservice.getUserByName(username);
		if(user==null) {
			//用户名不存在
			return null;
		}else {
			//验证密码,后台会将simpleAuthenticationInfo与token进行比对
			 SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword().toString(), getName());
			 return simpleAuthenticationInfo;
		}
		
	}

}
