package test.core.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.core.api.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByBirthDateBefore(LocalDate date);
}

package test.core.api.service;

import test.core.api.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    List<Employee> getEmployeesBornBeforeYear(int year);
}

package test.core.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesBornBeforeYear(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        return employeeRepository.findByBirthDateBefore(date);
    }
}

package test.core.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/born-before/{year}")
    public List<Employee> getEmployeesBornBeforeYear(@PathVariable int year) {
        return employeeService.getEmployeesBornBeforeYear(year);
    }
}


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
