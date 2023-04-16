package com.noobied.erp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noobied.erp.entity.Employee;
import com.noobied.erp.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
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
    void saveEmployeeDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(employeeOne);

        when(employeeService.saveEmployee(employeeOne)).thenReturn(employeeOne);
        this.mockMvc.perform(post("/api/employees/saveEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void getAllEmployeeDetails() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        this.mockMvc.perform(get("/api/employees/getAllEmployees"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getEmployeeDetailsById() throws Exception {
        when(employeeService.getEmployeeById(1)).thenReturn(employeeOne);
        this.mockMvc.perform(get("/api/employees/getEmployeeById/1"))
                    .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateEmployeeDetailsById() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(employeeOne);

        when(employeeService.updateEmployeeById(employeeOne, employeeOne.getId()))
                .thenReturn(employeeOne);
        this.mockMvc.perform(put("/api/employees/updateEmployee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteEmployeeById() throws Exception {
        doNothing().when(employeeService).deleteEmployeeById(employeeOne.getId());

        this.mockMvc.perform(delete("/api/employees/deleteEmployeeById/1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getEmployeeDetailsByEmail() throws Exception {
        when(employeeService.getEmployeeByEmail(employeeOne.getEmail())).thenReturn(employeeOne);

        this.mockMvc.perform(get("/api/employees/getEmployeeByEmail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("email",employeeOne.getEmail()))
                .andDo(print()).andExpect(status().isOk());
    }
}