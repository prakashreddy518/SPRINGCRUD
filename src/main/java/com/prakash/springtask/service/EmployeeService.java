package com.prakash.springtask.service;

import javax.validation.Valid;

import com.prakash.springtask.model.Employee;
import com.prakash.springtask.web.dto.EmployeeDTO;

public interface  EmployeeService {


	public Employee save(EmployeeDTO employeeDTO);


	public int updateEmployeeById(EmployeeDTO employeeDTO, long id);

}
