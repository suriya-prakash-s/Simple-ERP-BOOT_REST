package com.noobied.erp.repositiory;

import com.noobied.erp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepositry extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
