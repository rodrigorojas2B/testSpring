package com.example.EmployeeCoreApi.exception;

public class CannotDeleteEmployeeException extends RuntimeException {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}

package com.example.EmployeeCoreApi.service.impl;

import com.example.EmployeeCoreApi.exception.CannotDeleteEmployeeException;
import com.example.EmployeeCoreApi.model.Employee;
import com.example.EmployeeCoreApi.repository.EmployeeRepository;
import com.example.EmployeeCoreApi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElse(null);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Start of AI modification for HDU-EMP-003
    @Override
    public void deleteEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if ("Femenino".equalsIgnoreCase(employee.getGender())) {
                throw new CannotDeleteEmployeeException("No se puede eliminar a un empleado de género femenino.");
            } else {
                employeeRepository.deleteById(id);
            }
        } else {
            throw new RuntimeException("Empleado no encontrado con id: " + id);
        }
    }
    // End of AI modification for HDU-EMP-003
}
