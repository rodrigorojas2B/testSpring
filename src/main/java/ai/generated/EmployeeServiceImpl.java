package com.example.demo.service.impl;

import com.example.demo.exception.CannotDeleteEmployeeException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Other methods...

    @Override
    public void deleteEmployeeById(Long id) throws CannotDeleteEmployeeException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));

        // Start of AI modification
        if ("Femenino".equalsIgnoreCase(employee.getGender())) {
            throw new CannotDeleteEmployeeException("Cannot delete employee with gender 'Femenino'");
        }
        // End of AI modification

        employeeRepository.deleteById(id);
    }
}

---

