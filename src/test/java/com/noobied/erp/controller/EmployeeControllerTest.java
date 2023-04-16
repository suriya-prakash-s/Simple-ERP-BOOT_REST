package com.noobied.erp.controller;

import com.noobied.erp.entity.Employee;
import com.noobied.erp.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    Employee employeeOne;
    Employee employeeTwo;
    List<Employee> employeeList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        employeeOne = new Employee(1, "Suriya", "S", "suriyas@info.com");
        employeeTwo = new Employee(2, "Prakash", "P", "prakashp@onfo.com");
        employeeList.add(employeeOne);
        employeeList.add(employeeTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveEmployeeDetails() {
    }

    @Test
    void getAllEmployeeDetails() {
    }

    @Test
    void getEmployeeDetailsById() throws Exception {
        when(employeeService.getEmployeeById(1)).thenReturn(employeeOne);
        this.mockMvc.perform(get("/getEmployeeById/1"))
                    .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateEmployeeDetailsById() {
    }

    @Test
    void deleteEmployeeById() {
    }

    @Test
    void getEmployeeDetailsByEmail() {
    }
}