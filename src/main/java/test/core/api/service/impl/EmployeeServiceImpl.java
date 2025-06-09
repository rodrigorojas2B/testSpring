package test.core.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

@Component
public class EmployeeServiceImpl implements EmployeeService {
	
	    @Autowired
	    private EmployeeRepository repository;

	    public List<Employee> getAllEmployees() {
	        return (List<Employee>) repository.findAll();
	    }

	    public void deleteEmployeeById(Long id) {
	        repository.deleteById(id);
	    }

	    public Employee saveEmployee(Employee employee) {
	        return repository.save(employee); 
	    }
}