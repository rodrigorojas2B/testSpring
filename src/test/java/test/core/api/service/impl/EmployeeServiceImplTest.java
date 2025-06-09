package test.core.api.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetEmployeesBornBeforeYear() {
        Employee employee1 = new Employee();
        employee1.setBirthDate(LocalDate.of(1999, 1, 1));

        Employee employee2 = new Employee();
        employee2.setBirthDate(LocalDate.of(2001, 1, 1));

        when(employeeRepository.findByBirthDateBefore(any(LocalDate.class))).thenReturn(Arrays.asList(employee1));

        List<Employee> result = employeeService.getEmployeesBornBeforeYear(2000);

        assertEquals(1, result.size());
        assertEquals(employee1, result.get(0));
    }
}

