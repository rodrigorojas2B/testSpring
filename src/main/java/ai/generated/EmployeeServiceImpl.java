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
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Start of AI modification
    @Override
    public void deleteEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent() && "Femenino".equalsIgnoreCase(employee.get().getGender())) {
            throw new CannotDeleteEmployeeException("Cannot delete employee with id " + id + " because it is female.");
        }
        employeeRepository.deleteById(id);
    }
    // End of AI modification
}

2. Clase `CannotDeleteEmployeeException` nueva:

