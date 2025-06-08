package test.core.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            // Inicio de modificación por IA
            if ("Femenino".equals(employee.getGender())) {
                throw new CannotDeleteEmployeeException("No se puede eliminar un empleado de género femenino");
            }
            // Fin de modificación por IA
            employeeRepository.delete(employee);
        }
    }
}

