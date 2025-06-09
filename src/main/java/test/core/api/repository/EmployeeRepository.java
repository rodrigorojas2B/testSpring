package test.core.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.core.api.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByBirthDateBefore(LocalDate date);
}

