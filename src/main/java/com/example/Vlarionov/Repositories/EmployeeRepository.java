package com.example.Vlarionov.Repositories;

import com.example.Vlarionov.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public List<Employee> findBySurname(String surname);
    public List<Employee> findBySurnameContains(String surname);
}
