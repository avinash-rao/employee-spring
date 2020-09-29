package com.employee.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.exception.ResourceNotFoundException;
import com.employee.demo.model.Employee;
import com.employee.demo.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Employee getEmployeeById(@PathVariable(value = "id") int id) 
			throws ResourceNotFoundException{
		
		Employee employee = employeeService.findById(id);
		
//		if(employee == null) 
//			throw new ResourceNotFoundException("Employee not found for this id:"+id);

		return employee;
	}
	
	@PostMapping("")
	public Employee createEmployee(@Validated @RequestBody Employee employee) {
		System.out.println("creating emp");
		return employeeService.save(employee);
	}
	
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable(value = "id") int id,
			@Validated @RequestBody Employee employeeDetails) 
					throws ResourceNotFoundException {
		
		Employee employee = employeeService.findById(id);
		
		if(employee == null) {
			throw new ResourceNotFoundException("Id not found");
		}
		
		employee.setEmail(employeeDetails.getEmail());
		employee.setName(employeeDetails.getName());
		final Employee updatedEmployee = employeeService.save(employee);
		return updatedEmployee;
	}
	
	@DeleteMapping("/{id}")
	public Employee deleteEmployee(@PathVariable(value = "id") int id)
		throws ResourceNotFoundException {
		
		Employee employee = employeeService.findById(id);
		
		if(employee == null) 
			throw new ResourceNotFoundException("Id not found.");
		
		employeeService.delete(employee);
		return employee;
	}
}
