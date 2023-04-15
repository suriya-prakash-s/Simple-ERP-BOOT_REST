package com.noobied.erp.service;

import com.noobied.erp.entity.Employee;
import com.noobied.erp.repositiory.EmployeeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    //EmployeeRepositry instance injected auto by Autowire DI
    @Autowired
    private EmployeeRepositry employeeRepositry;
    //Save Employee Implementation
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepositry.save(employee);
    }
    //Get all employee implementation
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepositry.findAll();
    }
}
