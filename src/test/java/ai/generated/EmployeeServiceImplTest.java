package test.core.api.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void deleteFemaleEmployeeThrowsException() {
        Employee femaleEmployee = new Employee();
        femaleEmployee.setGender("Femenino");
        femaleEmployee = employeeRepository.save(femaleEmployee);

        assertThrows(CannotDeleteEmployeeException.class, () -> employeeService.deleteEmployeeById(femaleEmployee.getId()));
    }
}
