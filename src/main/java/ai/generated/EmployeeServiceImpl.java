package test.core.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {
            // Inicio de la modificación realizada por la IA
            if ("Femenino".equalsIgnoreCase(employee.getGender())) {
                throw new CannotDeleteEmployeeException("No se puede eliminar un empleado de género femenino");
            }
            // Fin de la modificación realizada por la IA
            employeeRepository.deleteById(employeeId);
        }
    }
}

