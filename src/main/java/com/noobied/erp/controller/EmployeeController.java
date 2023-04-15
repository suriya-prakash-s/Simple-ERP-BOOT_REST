package com.noobied.erp.controller;

import ch.qos.logback.core.model.conditional.ElseModel;
import com.noobied.erp.entity.Employee;
import com.noobied.erp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController=>@Controller+@ResponseBody
//Eliminates the use @ResponseBody on every call
@RestController()
@RequestMapping("/api/employees")
public class EmployeeController {
    //Autowiring the DI for EmployeeService
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/saveEmployee")
    public ResponseEntity<Employee> saveEmployeeDetails(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }


}