package com.tcore.crudTest.dao;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.tcore.crudTest.entities.Employee;
import com.tcore.crudTest.entities.User;

import java.util.List;
 
//括号后面的地址对应了sql文件的位置 表示类路径下www文件夹下的user.md文件
@SqlResource("user")
public interface UserDao extends BaseMapper<User> {
	//方法名对应了sql片段的名称
	User getUserByname(String username);
	String getRoleNameByUserName(String username);
	void addUser(User user);
	User findUserById(Integer id);
	void updateUser(User user);
	List<User> findByLimit(int i, int pageSize);
	int getUserCount();
	List<User> findSearchByLimit(String info, int i, int pageSize);
	int getUserSearchCount(String info);
}