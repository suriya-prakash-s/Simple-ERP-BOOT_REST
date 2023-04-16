package com.noobied.erp.service;

import com.noobied.erp.entity.Employee;
import com.noobied.erp.exception.ResourceNotFoundException;
import com.noobied.erp.repositiory.EmployeeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    //EmployeeRepositry instance injected auto by Autowire DI
    @Autowired
    private EmployeeRepositry employeeRepositry;

    public EmployeeServiceImpl(EmployeeRepositry employeeRepositry) {
        this.employeeRepositry = employeeRepositry;
    }

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
    //Get employee by id implementation
    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepositry.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Employee","Id",id));
    }
    //Update Employee by id implementation
    @Override
    public Employee updateEmployeeById(Employee employee, long id) {
        Employee existingEmployee = employeeRepositry.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee", "ID", id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        return employeeRepositry.save(existingEmployee);
    }
    //Delete employee by Id implementation
    @Override
    public void deleteEmployeeById(long id) {
        if(employeeRepositry.existsById(id)) {
            employeeRepositry.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Employee","ID",id);
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
       return employeeRepositry.findByEmail(email).
                orElseThrow(()->new ResourceNotFoundException("Employee","email",email));
    }
}
