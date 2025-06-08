package com.example.demo.service.impl;

import com.example.demo.exception.CannotDeleteEmployeeException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void deleteEmployeeById_FemaleEmployee_ThrowsException() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertThrows(CannotDeleteEmployeeException.class, () -> {
            employeeService.deleteEmployeeById(1L);
        });
    }
}
