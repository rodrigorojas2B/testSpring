package com.example.demo.service.impl;

import com.example.demo.exception.CannotDeleteEmployeeException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployeeById(Long id) {
        // Start of AI modification
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null && "Femenino".equals(employee.getGender())) {
            throw new CannotDeleteEmployeeException("Cannot delete female employee with id: " + id);
        }
        // End of AI modification
        employeeRepository.deleteById(id);
    }

    // Other existing methods...
}


