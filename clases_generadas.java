package test.core.api.exception;

public class CannotDeleteEmployeeException extends RuntimeException {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}

package test.core.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {
            // Inicio de la modificación realizada por la IA
            if ("Femenino".equalsIgnoreCase(employee.getGender())) {
                throw new CannotDeleteEmployeeException("No se puede eliminar un empleado de género femenino");
            }
            // Fin de la modificación realizada por la IA
            employeeRepository.deleteById(employeeId);
        }
    }
}

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
