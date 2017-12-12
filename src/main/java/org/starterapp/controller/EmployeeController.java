package org.starterapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.starterapp.entity.Employee;
import org.starterapp.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable Integer id) {
		return employeeRepository.findOne(id);
    }
	
	@RequestMapping(value = "/put", method = RequestMethod.PUT)
    public ResponseEntity<String> putEmployee(@RequestBody Employee e) {
        try {
        	employeeRepository.save(e);
            return new ResponseEntity<String>("{ \"status\": \"success\", \"insertedId\": "+ e.getId() +"}", HttpStatus.CREATED);
        } catch(Exception ex) {
        	return new ResponseEntity<String>("{ \"status\": \"failed\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
		try {
			employeeRepository.delete(id);
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
    }

}
