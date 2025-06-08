package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.exception.CannotDeleteEmployeeException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        // Inicio de la modificación por la IA
        if (employee != null && "Femenino".equalsIgnoreCase(employee.getGender())) {
            throw new CannotDeleteEmployeeException("No se puede eliminar un empleado de género femenino.");
        }
        // Fin de la modificación por la IA

        employeeRepository.deleteById(id);
    }

    // Resto de los métodos existentes...
}

--- NUEVA CLASE JAVA GENERADA ---

