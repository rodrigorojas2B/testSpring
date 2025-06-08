package test.core.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployeeById(Long id) {
        // Start of AI modification
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null && "Femenino".equals(employee.getGender())) {
            throw new CannotDeleteEmployeeException("Cannot delete female employee with id: " + id);
        }
        // End of AI modification
        employeeRepository.deleteById(id);
    }

    // Other existing methods...
}

2. Nueva clase `CannotDeleteEmployeeException`:

