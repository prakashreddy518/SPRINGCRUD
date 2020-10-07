package com.prakash.springtask.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prakash.springtask.model.Employee;
import com.prakash.springtask.model.User;
import com.prakash.springtask.repository.EmployeeRepository;
import com.prakash.springtask.repository.UserRepository;
import com.prakash.springtask.service.EmployeeService;
import com.prakash.springtask.service.UserService;
import com.prakash.springtask.web.dto.EmployeeDTO;


@Controller
public class EmployeeManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeManagementController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@ModelAttribute("employee")
    public EmployeeDTO employeeDTO() {
        return new EmployeeDTO();
    }
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String employeedetail(Model model) {
		try {
			logger.info("List Of employees");
		model.addAttribute("employees", employeeRepository.findAll());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	@RequestMapping(value = "/employeeRegistration", method = RequestMethod.GET)
	public String showRegistrationForm() {
		return "employeeRegistration";
	}
//	@RequestMapping(value = "/employeeRegistration", method = RequestMethod.GET)
//	public ModelAndView login(){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("employee",employee);
//		modelAndView.setViewName("employeeRegistration");
//		return modelAndView;
//		
//	}
	
	@RequestMapping(value = "/employeeRegistrations", method = RequestMethod.POST)
	public String registerEmployeeAccount(@ModelAttribute("employee") EmployeeDTO employeeDTO) {
		try {
			logger.info("Employee Registration Started");
		employeeService.save(employeeDTO);
		logger.info("Employee Registration Succesfull");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		try {
			logger.info("Editing employeee");
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
		model.addAttribute("employee", employee);
		logger.info("Edited employee");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "update-Employee";
	}
	
	@RequestMapping(value= "/update/{id}", method = RequestMethod.POST)
	public String updateEmployee(EmployeeDTO employeeDTO,@PathVariable("id") long id, BindingResult result,Model model)
			{
		try {
			logger.info("Updating Employee");
	 int employees = employeeService.updateEmployeeById(employeeDTO,id);
	 model.addAttribute("employees", employeeRepository.findAll());
	 logger.info("Employee Updated Succesfull");
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Employe not updated successfully");
		}
	 return "index";
	 }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable("id") long id, Model model) {
		try {
			logger.info("Deleting Employeee");
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid emloyee Id:" + id));
		employeeRepository.delete(employee);
		logger.info("Employee Deleted Successfully");
		model.addAttribute("employees", employeeRepository.findAll());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
}
