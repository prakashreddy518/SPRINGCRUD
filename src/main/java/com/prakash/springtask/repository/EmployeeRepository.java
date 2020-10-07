package com.prakash.springtask.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prakash.springtask.model.Employee;

@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Modifying
    @Transactional
	@Query(value="update employee set first_name=:firstName ,last_name=:lastName,email=:email,age=:age,gender=:gender,address=:address where id =:ids",nativeQuery=true
		)public int updateEmployeeById(@Param(value="ids")long ids,@Param(value="firstName") String firstName,@Param(value="lastName") String lastName,@Param(value="email") String email,
				@Param(value="age")long age,@Param(value="gender")String gender,@Param(value="address")String address);
	
}