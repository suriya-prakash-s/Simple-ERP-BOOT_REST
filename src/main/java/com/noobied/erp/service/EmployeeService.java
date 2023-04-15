package com.noobied.erp.service;

import com.noobied.erp.entity.Employee;

import java.util.List;

public interface EmployeeService {

    //save employee details
    Employee saveEmployee(Employee employee);
    //get all employees
    List<Employee> getAllEmployees();
}
