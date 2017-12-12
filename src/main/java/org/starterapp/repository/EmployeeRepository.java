package org.starterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.starterapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> { }