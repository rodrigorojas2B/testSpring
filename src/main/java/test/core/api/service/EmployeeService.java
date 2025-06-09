package test.core.api.service;

import java.util.List;

import test.core.api.model.Employee;

public interface EmployeeService {
	

	List<Employee> getAllEmployees();
	void deleteEmployeeById(Long id);
    Employee saveEmployee(Employee employee);

	
}