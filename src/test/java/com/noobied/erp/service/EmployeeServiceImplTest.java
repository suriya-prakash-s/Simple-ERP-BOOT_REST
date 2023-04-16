package com.noobied.erp.service;

import com.noobied.erp.entity.Employee;
import com.noobied.erp.repositiory.EmployeeRepositry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@DataJpaTest
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepositry employeeRepositry;
    private EmployeeService employeeService;
    Employee employee;
    AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepositry);
        employee = new Employee(1, "Suriya", "S", "suriyas@info.com");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveEmployee() {
        mock(Employee.class);
        mock(EmployeeRepositry.class);

        when(employeeRepositry.save(employee)).thenReturn(employee);
        assertThat(employeeService.saveEmployee(employee)).isEqualTo(employee);
    }

    @Test
    void getAllEmployees() {
        mock(Employee.class);
        mock(EmployeeRepositry.class);

        when(employeeRepositry.findAll()).thenReturn(
                new ArrayList<Employee>(Collections.singleton(employee))
        );

        assertThat(employeeService.getAllEmployees().get(0).getId()).isEqualTo(employee.getId());

    }

    @Test
    void getEmployeeById() {
        mock(Employee.class);
        mock(EmployeeRepositry.class);
        when(employeeRepositry.findById(employee.getId())).thenReturn(Optional.ofNullable(employee));
        assertThat(employeeService.getEmployeeById(employee.getId())).isEqualTo(employee);
    }

    @Test
    void updateEmployeeById() {
        mock(Employee.class);
        mock(EmployeeRepositry.class);
        when(employeeRepositry.findById(employee.getId())).thenReturn(Optional.ofNullable(employee));
        when(employeeRepositry.save(employee)).thenReturn(employee);
        assertThat(employeeService.updateEmployeeById(employee, employee.getId())).isEqualTo(employee);
    }

    @Test
    void deleteEmployeeById() {
        mock(Employee.class);
        mock(EmployeeRepositry.class, Mockito.CALLS_REAL_METHODS);
        when(employeeRepositry.existsById(employee.getId())).thenReturn(true);
        doAnswer(Answers.CALLS_REAL_METHODS).when(employeeRepositry).deleteById(any());
        assertDoesNotThrow(()->employeeService.deleteEmployeeById(employee.getId()));
    }

    @Test
    void getEmployeeByEmail() {
        mock(Employee.class);
        mock(EmployeeRepositry.class);
        when(employeeRepositry.findByEmail(employee.getEmail())).thenReturn(Optional.ofNullable(employee));
        assertThat(employeeService.getEmployeeByEmail(employee.getEmail())).isEqualTo(employee);
    }
}