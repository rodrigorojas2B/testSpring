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
