package com.example.EmployeeCoreApi.service.impl;

import com.example.EmployeeCoreApi.exception.CannotDeleteEmployeeException;
import com.example.EmployeeCoreApi.model.Employee;
import com.example.EmployeeCoreApi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteEmployeeById_WithFemaleEmployee_ThrowsException() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        assertThrows(CannotDeleteEmployeeException.class, () -> {
            employeeService.deleteEmployeeById(1L);
        });

        verify(employeeRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testDeleteEmployeeById_WithNonFemaleEmployee_DeletesEmployee() {
        Employee employee = new Employee();
        employee.setGender("Masculino");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository).deleteById(anyLong());
    }
}
