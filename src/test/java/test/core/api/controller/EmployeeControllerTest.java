package test.core.api.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeesBornBeforeYear() {
        Employee emp1 = new Employee();
        emp1.setBirthDate(LocalDate.of(1990, 1, 1));
        Employee emp2 = new Employee();
        emp2.setBirthDate(LocalDate.of(1995, 1, 1));
        when(employeeService.getEmployeesBornBeforeYear(2000)).thenReturn(Arrays.asList(emp1, emp2));

        List<Employee> result = employeeController.getEmployeesBornBeforeYear(2000);

        assertEquals(2, result.size());
        assertEquals(emp1, result.get(0));
        assertEquals(emp2, result.get(1));
    }
}
