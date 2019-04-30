package com.tcore.crudTest.dao;


import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.tcore.crudTest.entities.Employee;

@SqlResource("employee")
public interface EmployeeDao extends BaseMapper<Employee>{
		public void insertEmp(Employee emp);
		public int updateEmp(Employee emp);
		public int getEmpCount();
		public List<Employee> findByLimit(int i, int pageSize);
		public List<Employee> searchEmp(Object info);
		public List<Employee> findSearchByLimit(String info, Integer i,int pageSize);
		public int getEmpSearchCount(String info);
}
