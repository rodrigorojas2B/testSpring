package com.example.demo.service.impl;

import com.example.demo.exception.CannotDeleteEmployeeException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployeeById(Long id) {
        // Start of AI modification
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null && "Femenino".equals(employee.getGender())) {
            throw new CannotDeleteEmployeeException("Cannot delete female employee with id: " + id);
        }
        // End of AI modification
        employeeRepository.deleteById(id);
    }

    // Other existing methods...
}


package com.example.demo.exception;

public class CannotDeleteEmployeeException extends RuntimeException {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}

3. Test Unitario para `EmployeeServiceImpl`:

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
