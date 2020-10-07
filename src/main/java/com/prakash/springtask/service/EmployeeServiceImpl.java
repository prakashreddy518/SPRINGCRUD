package com.prakash.springtask.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prakash.springtask.model.Employee;
import com.prakash.springtask.repository.EmployeeRepository;
import com.prakash.springtask.web.dto.EmployeeDTO;
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	
	
    @Autowired
    private EmployeeRepository employeeRepository;
    
	@Override
	public Employee save(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		
		employee.setAddress(employeeDTO.getAddress());
		employee.setAge(employeeDTO.getAge());
		employee.setEmail(employeeDTO.getEmail());
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setGender(employeeDTO.getGender());
		employee.setLastName(employeeDTO.getLastName());
		Employee saveEmployee = employeeRepository.save(employee);
		return saveEmployee;
		
		
		
	}

	@Transactional
	public Employee updateEmployee(@Valid Employee employee) {
		return employee;
		// TODO Auto-generated method stub
		
	}


	@Override
	public int updateEmployeeById(EmployeeDTO employeeDTO, long id) {
		long ids = id;
		String firstName = employeeDTO.getFirstName();
		String LastName = employeeDTO.getLastName();
		String email = employeeDTO.getEmail();
		int  age = employeeDTO.getAge();
		String gender = employeeDTO.getGender();
		String address = employeeDTO.getAddress();
		return employeeRepository.updateEmployeeById(ids,firstName,LastName,email,age,gender,address);
	}

}
