package com.example.demo.service;

import com.example.demo.exception.CannotDeleteEmployeeException;

public interface EmployeeService {
    // Other methods...

    void deleteEmployeeById(Long id) throws CannotDeleteEmployeeException;
}

---

