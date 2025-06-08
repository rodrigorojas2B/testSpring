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

package com.example.employeecoreapi.exception;

public class CannotDeleteEmployeeException extends RuntimeException {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}

3. Pruebas unitarias para `EmployeeServiceImpl`:

package com.example.employeecoreapi.service.impl;

import com.example.employeecoreapi.exception.CannotDeleteEmployeeException;
import com.example.employeecoreapi.model.Employee;
import com.example.employeecoreapi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deleteEmployeeById_FemaleEmployee_ThrowsException() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setGender("Femenino");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertThrows(CannotDeleteEmployeeException.class, () -> employeeService.deleteEmployeeById(1L));
        verify(employeeRepository, never()).deleteById(1L);
    }

    @Test
    public void deleteEmployeeById_MaleEmployee_DeletesEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setGender("Masculino");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(1L);
        verify(employeeRepository).deleteById(1L);
    }

    @Test
    public void deleteEmployeeById_EmployeeDoesNotExist_DeletesEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        employeeService.deleteEmployeeById(1L);
        verify(employeeRepository).deleteById(1L);
    }
}
