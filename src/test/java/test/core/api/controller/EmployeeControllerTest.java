package test.core.api.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeesBornBeforeYear() {
        Employee employee1 = new Employee();
        employee1.setBirthDate(LocalDate.of(1999, 1, 1));

        when(employeeService.getEmployeesBornBeforeYear(anyInt())).thenReturn(Arrays.asList(employee1));

        List<Employee> result = employeeController.getEmployeesBornBeforeYear(2000);

        assertEquals(1, result.size());
        assertEquals(employee1, result.get(0));
    }
}
