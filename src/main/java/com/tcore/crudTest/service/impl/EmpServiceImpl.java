package com.tcore.crudTest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcore.crudTest.dao.EmployeeDao;
import com.tcore.crudTest.entities.Employee;
import com.tcore.crudTest.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmployeeDao employeeDao;
	@Override
	public List<Employee> findAll() {
		
		return employeeDao.all();
	}
	@Override
	public void addEmp(Employee emp) {
		System.out.println("service...."+emp);
//		employeeDao.insert(emp, true);
	//	employeeDao.insertTemplate(emp);
		employeeDao.insertEmp(emp);;
	}
	
	@Override
	public Employee findById(Integer id) {
		// TODO Auto-generated method stub
		return employeeDao.unique(id);
	}
	@Override
	public int updateEmp(Employee emp) {
		// TODO Auto-generated method stub
	//	return employeeDao.updateById(emp);
		return employeeDao.updateEmp(emp);
	}
	@Override
	public int deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		return employeeDao.deleteById(id);
	}
	//获取总首页数
	@Override
	public int getTotalpages(int pageSize) throws Exception {
		//首先是获取总记录数
		int count  =	employeeDao.getEmpCount();
		if(count%pageSize==0) {
			return count/pageSize;
		}else {
			return count/pageSize+1;
		}
	}
	@Override
	public List<Employee> findByLimit(int page, int pageSize) throws Exception {
		int i = (page-1)*pageSize ;
	return	employeeDao.findByLimit(i,pageSize);
		
	}
	@Override
	public List<Employee> searchEmp(String info) {
		return employeeDao.searchEmp(info);
	}
	@Override
	public List<Employee> findSearchByLimit(String info, Integer page,int pageSize) {
			int i = (page-1)*pageSize ;
		return employeeDao.findSearchByLimit(info,i,pageSize);
	}
	@Override
	public int getSearchTotalpages(int pageSize,String info) {
		//首先是获取总记录数
		int count  =	employeeDao.getEmpSearchCount(info);
		if(count%pageSize==0) {
			return count/pageSize;
		}else {
			return count/pageSize+1;
		}
	}
	
	
	
}
