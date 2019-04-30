package com.tcore.crudTest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcore.crudTest.dao.UserDao;
import com.tcore.crudTest.entities.Employee;
import com.tcore.crudTest.entities.User;
import com.tcore.crudTest.service.UserService;

@Service
public class UserserviceImpl implements UserService {
	@Autowired
	UserDao userdao;

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return userdao.getUserByname(username);
	}

	@Override
	public String getRoleNameByUserName(String username) {
		// TODO Auto-generated method stub
		return userdao.getRoleNameByUserName(username);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userdao.all();
	}

	//添加用户
	@Override
	public void addUser(User user) {
		 userdao.addUser(user);
	}

	@Override
	public User findUserById(Integer id) {
		return userdao.findUserById(id);
	}

	@Override
	public void updateUser(User user) {
		userdao.updateUser(user);
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userdao.deleteById(id);
	}

	@Override
	public List<User> findByLimit(Integer page, int pageSize) {
		// TODO Auto-generated method stub
		int i = (page-1)*pageSize ;
		return userdao.findByLimit(i,pageSize);
	}

	@Override
	public int getTotalpages(int pageSize) {
		//首先是获取总记录数
		int count  =	userdao.getUserCount();
		if(count%pageSize==0) {
			return count/pageSize;
		}else {
			return count/pageSize+1;
		}
	}

	@Override
	public List<User> findSearchByLimit(String info, Integer page, int pageSize) {
		int i = (page-1)*pageSize ;
		return userdao.findSearchByLimit(info,i,pageSize);
	}

	@Override
	public int getSearchTotalpages(int pageSize, String info) {
				//首先是获取总记录数
				int count  =	userdao.getUserSearchCount(info);
				if(count%pageSize==0) {
					return count/pageSize;
				}else {
					return count/pageSize+1;
				}
	}

}
