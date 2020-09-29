package com.employee.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public Employee findById(int id) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		return employee;
	}
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}
}
