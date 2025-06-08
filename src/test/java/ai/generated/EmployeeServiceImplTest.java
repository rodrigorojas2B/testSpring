package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.exception.CannotDeleteEmployeeException;
import com.example.demo.repository.EmployeeRepository;
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
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteEmployeeById_FemaleEmployee() {
        Employee femaleEmployee = new Employee();
        femaleEmployee.setGender("Femenino");

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(femaleEmployee));

        assertThrows(CannotDeleteEmployeeException.class, () -> {
            employeeService.deleteEmployeeById(1L);
        });

        verify(employeeRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testDeleteEmployeeById_MaleEmployee() {
        Employee maleEmployee = new Employee();
        maleEmployee.setGender("Masculino");

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(maleEmployee));

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testDeleteEmployeeById_UnspecifiedGenderEmployee() {
        Employee unspecifiedGenderEmployee = new Employee();

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(unspecifiedGenderEmployee));

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository, times(1)).deleteById(anyLong());
    }
}
