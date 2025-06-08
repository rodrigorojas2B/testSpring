package com.example.employeecoreapi.service.impl;

import com.example.employeecoreapi.exception.CannotDeleteEmployeeException;
import com.example.employeecoreapi.model.Employee;
import com.example.employeecoreapi.repository.EmployeeRepository;
import com.example.employeecoreapi.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            // Begin of modification by AI
            if ("Femenino".equals(employee.getGender())) {
                throw new CannotDeleteEmployeeException("Cannot delete employee of gender 'Femenino'");
            }
            // End of modification by AI
            employeeRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid employee Id:" + id);
        }
    }
}
```

```java
