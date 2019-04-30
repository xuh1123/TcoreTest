package com.tcore.crudTest.service;

import java.util.List;

import com.tcore.crudTest.entities.Employee;


public interface EmpService {
	//分页操作
	List<Employee> findByLimit(int page,int pageSize) throws Exception;
	//获取总页数
	int getTotalpages(int pageSize) throws Exception; 
	List<Employee> findAll();
	void addEmp(Employee emp);
	Employee findById(Integer id);
	int updateEmp(Employee emp);
	int deleteEmp(Integer id);
	List<Employee> searchEmp(String info);
	List<Employee> findSearchByLimit(String info, Integer page, int pageSize);
	int getSearchTotalpages(int pageSize, String info);
}
