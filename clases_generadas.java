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

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            // Inicio de modificación por IA
            if ("Femenino".equals(employee.getGender())) {
                throw new CannotDeleteEmployeeException("No se puede eliminar un empleado de género femenino");
            }
            // Fin de modificación por IA
            employeeRepository.delete(employee);
        }
    }
}

package test.core.api.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testDeleteFemaleEmployeeThrowsException() {
        Employee femaleEmployee = new Employee();
        femaleEmployee.setGender("Femenino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(femaleEmployee));

        assertThrows(CannotDeleteEmployeeException.class, () -> employeeService.deleteEmployeeById(1L));
    }

    @Test
    public void testDeleteMaleEmployeeDoesNotThrowException() {
        Employee maleEmployee = new Employee();
        maleEmployee.setGender("Masculino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(maleEmployee));

        employeeService.deleteEmployeeById(1L);
    }
}
