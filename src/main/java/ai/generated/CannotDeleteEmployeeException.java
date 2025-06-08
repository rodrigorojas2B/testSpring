package com.example.employeecoreapi.exception;

public class CannotDeleteEmployeeException extends RuntimeException {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}

3. Pruebas unitarias para `EmployeeServiceImpl`:

