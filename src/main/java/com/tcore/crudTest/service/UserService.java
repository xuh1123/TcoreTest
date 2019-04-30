package com.tcore.crudTest.service;


import java.util.List;

import com.tcore.crudTest.entities.User;
 

public interface UserService {
	User getUserByName(String username); 
	String getRoleNameByUserName(String username);
	List<User> getAll();
	void addUser(User user);
	User findUserById(Integer id);
	void updateUser(User user);
	void deleteUser(Integer id);
	List<User> findByLimit(Integer page, int pageSize);
	int getTotalpages(int pageSize);
	List<User> findSearchByLimit(String info, Integer page, int pageSize);
	int getSearchTotalpages(int pageSize, String info);
 
}