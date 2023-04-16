package com.noobied.erp.controller;

import ch.qos.logback.core.model.conditional.ElseModel;
import com.noobied.erp.dto.ResponseHandler;
import com.noobied.erp.entity.Employee;
import com.noobied.erp.exception.ResourceNotFoundException;
import com.noobied.erp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Object> saveEmployeeDetails(@RequestBody Employee employee) {
        ResponseEntity<Object> response = ResponseHandler.responseBuilder(
                "SAVED SUCCESSFULLY",
                HttpStatus.CREATED,
                employeeService.saveEmployee(employee));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //Get all employee API
    @GetMapping("/getAllEmployees")
    public ResponseEntity<Object> getAllEmployeeDetails() {
        ResponseEntity<Object> response = ResponseHandler.responseBuilder(
                "FETCHED ALL RECORDS",
                HttpStatus.OK,
                employeeService.getAllEmployees());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Get Employee by id API
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Object> getEmployeeDetailsById(@PathVariable("id") long id) {
        try {
            ResponseEntity<Object> response = ResponseHandler.responseBuilder(
                    "EMPLOYEE FOUND",
                    HttpStatus.OK,
                    employeeService.getEmployeeById(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            ResponseEntity<Object> response = ResponseHandler.responseBuilder(
                    "EMPLOYEE NOT FOUND",
                    HttpStatus.NOT_FOUND,
                    Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    //Update employee by id
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Object> updateEmployeeDetailsById(@RequestBody Employee employee,
                                                              @PathVariable("id") long id) {
        try {
            ResponseEntity<Object> response = ResponseHandler.responseBuilder(
                    "UPDATED SUCCESSFULLY",
                    HttpStatus.OK,
                    employeeService.updateEmployeeById(employee, id));
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (ResourceNotFoundException e){
            ResponseEntity<Object> response = ResponseHandler.responseBuilder(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    //delete employee by id
    @DeleteMapping("deleteEmployeeById/{id}")
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable("id") long id) {
        try {
            ResponseEntity<Object> response = ResponseHandler.responseBuilder(
                    "DELETED SUCCESSFULLY",
                    HttpStatus.OK,
                    Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            ResponseEntity<Object> response = ResponseHandler.responseBuilder(
                    "RECORD NOT FOUND",
                    HttpStatus.NOT_FOUND,
                    Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    //get employee by email
    @GetMapping("/getEmployeeByEmail")
    public ResponseEntity<Object> getEmployeeDetailsByEmail(@RequestParam(name = "email") String email){
        try {
            ResponseEntity<Object> response = ResponseHandler.responseBuilder(
                    "EMPLOYEE FOUND",
                    HttpStatus.OK,
                    employeeService.getEmployeeByEmail(email));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            ResponseEntity<Object> response = ResponseHandler.responseBuilder(
                    "EMPLOYEE NOT FOUND",
                    HttpStatus.NOT_FOUND,
                    Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
