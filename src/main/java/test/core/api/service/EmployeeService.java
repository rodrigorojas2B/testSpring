package test.core.api.service;

import test.core.api.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    List<Employee> getEmployeesBornBeforeYear(int year);
}

