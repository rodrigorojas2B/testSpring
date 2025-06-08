--- CÓDIGO JAVA GENERADO ---

package com.example.demo.service;

import com.example.demo.exception.CannotDeleteEmployeeException;

public interface EmployeeService {
    // Other methods...

    void deleteEmployeeById(Long id) throws CannotDeleteEmployeeException;
}

---

package com.example.demo.service.impl;

import com.example.demo.exception.CannotDeleteEmployeeException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Other methods...

    @Override
    public void deleteEmployeeById(Long id) throws CannotDeleteEmployeeException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));

        // Start of AI modification
        if ("Femenino".equalsIgnoreCase(employee.getGender())) {
            throw new CannotDeleteEmployeeException("Cannot delete employee with gender 'Femenino'");
        }
        // End of AI modification

        employeeRepository.deleteById(id);
    }
}

---

package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CannotDeleteEmployeeException extends RuntimeException {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}

---

package com.example.demo.service.impl;

import com.example.demo.exception.CannotDeleteEmployeeException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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
    public void deleteEmployeeById_GenderFemenino_ThrowsCannotDeleteEmployeeException() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertThrows(CannotDeleteEmployeeException.class, () -> {
            employeeService.deleteEmployeeById(1L);
        });
    }
}
