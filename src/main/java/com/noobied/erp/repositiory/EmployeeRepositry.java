package com.noobied.erp.repositiory;

import com.noobied.erp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeRepositry extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
