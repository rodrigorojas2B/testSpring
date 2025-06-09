package test.core.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import test.core.api.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

}


