package com.krishna.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.krishna.employee.entity.Employee;

@Repository
@CrossOrigin("http://localhost:4200")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
