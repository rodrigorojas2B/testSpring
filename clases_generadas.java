
package com.example.EmployeeCoreApi.service.impl;

import com.example.EmployeeCoreApi.exception.CannotDeleteEmployeeException;
import com.example.EmployeeCoreApi.model.Employee;
import com.example.EmployeeCoreApi.repository.EmployeeRepository;
import com.example.EmployeeCoreApi.service.EmployeeService;
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
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            // Start of AI modification
            if ("Femenino".equals(employee.get().getGender())) {
                throw new CannotDeleteEmployeeException("Cannot delete female employee due to internal policy.");
            }
            // End of AI modification
            employeeRepository.deleteById(id);
        }
    }
}

package com.example.EmployeeCoreApi.exception;

public class CannotDeleteEmployeeException extends RuntimeException {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}

package com.example.EmployeeCoreApi.service.impl;

import com.example.EmployeeCoreApi.exception.CannotDeleteEmployeeException;
import com.example.EmployeeCoreApi.model.Employee;
import com.example.EmployeeCoreApi.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {

    private EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepository);

    @Test
    public void testDeleteFemaleEmployeeThrowsException() {
        Employee femaleEmployee = new Employee();
        femaleEmployee.setGender("Femenino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(femaleEmployee));

        assertThrows(CannotDeleteEmployeeException.class, () -> employeeService.deleteEmployeeById(1L));
    }
}
