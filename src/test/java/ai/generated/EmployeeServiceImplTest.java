package test.core.api.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteEmployeeById_FemaleEmployee() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        assertThrows(CannotDeleteEmployeeException.class, () -> {
            employeeService.deleteEmployeeById(1L);
        });

        verify(employeeRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testDeleteEmployeeById_MaleEmployee() {
        Employee employee = new Employee();
        employee.setGender("Masculino");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository, times(1)).deleteById(anyLong());
    }
}
