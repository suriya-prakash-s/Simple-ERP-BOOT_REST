package com.noobied.erp.repositiory;

import com.noobied.erp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepositry extends JpaRepository<Employee, Long> {
}
