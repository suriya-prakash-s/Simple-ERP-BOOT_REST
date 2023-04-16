package com.noobied.erp.repository;

import com.noobied.erp.entity.Employee;
import com.noobied.erp.repositiory.EmployeeRepositry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepositry employeeRepositry;
    Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(2, "Suriya", "Prakash", "suriya@info.com");
        employeeRepositry.save(employee);
    }

    @AfterEach
    void tearDown() {
        employee = null;
        employeeRepositry.deleteAll();
    }

    //Test Case - findByEmail_FOUND
    @Test
    void testFindByEmail_Found() {
        Optional<Employee> optionalEmployee = employeeRepositry.findByEmail("suriya@info.com");
        if(optionalEmployee.isPresent()){
            assertThat(optionalEmployee.get().getId()).isEqualTo(employee.getId());
        }
    }
    //Test Case - findByEmail_NOT_FOUND
    @Test
    void testFindByEmail_Not_Found() {
        Optional<Employee> optionalEmployee = employeeRepositry.findByEmail("manick@info.com");
        assertThat(optionalEmployee.isEmpty()).isTrue();
    }
}
