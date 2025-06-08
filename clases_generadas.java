package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.exception.CannotDeleteEmployeeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Other methods...

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + id));

        // Start of AI modification: HDU-EMP-003
        if ("Femenino".equals(employee.getGender())) {
            throw new CannotDeleteEmployeeException("Cannot delete employee of gender 'Femenino'");
        }
        // End of AI modification: HDU-EMP-003

        employeeRepository.delete(employee);
    }
}

package com.example.demo.exception;

public class CannotDeleteEmployeeException extends RuntimeException {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}
