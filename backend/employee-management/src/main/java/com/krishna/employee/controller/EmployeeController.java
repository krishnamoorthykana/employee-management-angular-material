package com.krishna.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.employee.entity.Employee;
import com.krishna.employee.exception.ResourceNotFoundException;
import com.krishna.employee.repository.EmployeeRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepo;
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/employees")
	public List<Employee>getAllEmployess(){
		
		return empRepo.findAll();
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		
		return empRepo.save(employee);
		
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		
	Employee emp = empRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee not found with id"+id));
		
		return ResponseEntity.ok(emp); 
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee( @PathVariable int id, @RequestBody Employee employee) {
		Employee emp = empRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee not found with id"+id));
		
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setSalary(employee.getSalary());
		empRepo.save(emp);
		return ResponseEntity.ok(emp); 
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteById(@PathVariable int id) {
		Employee emp = empRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee not found with id"+id));
		empRepo.delete(emp);
		
	}
	
//	@DeleteMapping("/employees/{id}")
//	public ResponseEntity<Map<String,Boolean>> deleteById(@PathVariable int id) {
//		Employee emp = empRepo.findById(id).orElseThrow(
//				()-> new ResourceNotFoundException("Employee not found with id"+id));
//		empRepo.delete(emp);
//		Map<String,Boolean>response = new HashMap<String,Boolean>();
//		response.put("Deleted", Boolean.TRUE);
//		return ResponseEntity.ok(response);
//		
//	}
}
