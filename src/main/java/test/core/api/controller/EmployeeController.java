package test.core.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    
    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getAll() {
        System.out.println("Fetching all employees");
        return service.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public Employee insert(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

   
}





