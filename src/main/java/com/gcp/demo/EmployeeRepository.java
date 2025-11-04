package com.gcp.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Employee findByNameIgnoreCase(String name);

    Employee findByEmail(String email);
}
