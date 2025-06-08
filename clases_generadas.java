--- CÓDIGO GENERADO ---

package com.example.employeecoreapi.exception;

public class CannotDeleteEmployeeException extends RuntimeException {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}

---

package com.example.employeecoreapi.service.impl;

import com.example.employeecoreapi.exception.CannotDeleteEmployeeException;
import com.example.employeecoreapi.model.Employee;
import com.example.employeecoreapi.repository.EmployeeRepository;
import com.example.employeecoreapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.setName(employeeDetails.getName());
        employee.setGender(employeeDetails.getGender());
        employee.setDepartment(employeeDetails.getDepartment());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        // Start of AI modification
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        if (employee.getGender().equalsIgnoreCase("Femenino")) {
            throw new CannotDeleteEmployeeException("Cannot delete employee with gender 'Femenino'");
        }
        // End of AI modification
        employeeRepository.deleteById(id);
    }
}
