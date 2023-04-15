package com.noobied.erp.controller;

import ch.qos.logback.core.model.conditional.ElseModel;
import com.noobied.erp.entity.Employee;
import com.noobied.erp.exception.ResourceNotFoundException;
import com.noobied.erp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController=>@Controller+@ResponseBody
//Eliminates the use @ResponseBody on every call
@RestController()
@RequestMapping("/api/employees")
public class EmployeeController {
    //Autowiring the DI for EmployeeService
    @Autowired
    private EmployeeService employeeService;
    //Save Employee API
    @PostMapping("/saveEmployee")
    public ResponseEntity<Employee> saveEmployeeDetails(@RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }
    //Get all employee API
    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployeeDetails() {
        return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(),HttpStatus.OK);
    }

    //Get Employee by id API
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeDetailsById(@PathVariable("id") long id) {
            return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }


}
