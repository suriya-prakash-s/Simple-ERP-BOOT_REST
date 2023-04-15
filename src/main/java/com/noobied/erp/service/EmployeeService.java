package com.noobied.erp.service;

import com.noobied.erp.entity.Employee;

import java.util.List;

public interface EmployeeService {

    //save employee details
    Employee saveEmployee(Employee employee);
    //get all employees
    List<Employee> getAllEmployees();

    //get employee by id
    Employee getEmployeeById(long id);

    //update employee by id
    Employee updateEmployeeById(Employee employee, long id);

    //delete employee by id
    void deleteEmployeeById(long id);
}
