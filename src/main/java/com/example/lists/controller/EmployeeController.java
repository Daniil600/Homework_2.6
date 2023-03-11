package com.example.lists.controller;

import com.example.lists.exception.EmployeeAlreadyAddedException;
import com.example.lists.exception.EmployeeNotFoundException;
import com.example.lists.exception.EmployeeStorageIsFullException;
import com.example.lists.service.EmployeeService;
import com.example.lists.object.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RequestMapping(path = "/employee")
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String handleException(EmployeeAlreadyAddedException e){
        return String.format("%s %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleException(EmployeeNotFoundException e){
        return String.format("%s %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public String handleException(EmployeeStorageIsFullException e){
        return String.format("%s %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    @GetMapping
    public Collection<Employee> hello(){
        return employeeService.showAll();
    }
    @GetMapping(path = "/add")
    public Employee add(@RequestParam(name = "firstName") String firstName,
                        @RequestParam(name = "lastName") String lastName,
                        @RequestParam(name = "sallary") int sallary,
                        @RequestParam(name = "departament") int departament){
        return employeeService.addEmployee(firstName, lastName, sallary, departament);
    }
    @GetMapping(path = "/remove")
    public Employee del(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return employeeService.delEmployee(firstName,lastName);
    }
    @GetMapping(path = "/find")
    public Employee find(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return employeeService.find(firstName,lastName);
    }
    @GetMapping(path = "/departments/maxsalary")
    public Employee maxSalary(@RequestParam(name = "dep")int department){
        return employeeService.findMaxSallary(department);
    }

    @GetMapping(path = "/departments/minsalary")
    public Employee minSalary(@RequestParam(name = "dep")int department){
        return employeeService.findMinSalary(department);
    }
    @GetMapping(path = "/departments/allofone")
    public Collection<Employee> allDep(@RequestParam(name = "dep")int department){
        return employeeService.allOfOneDepartment(department);
    }

    @GetMapping(path = "/departments/all")
    public Collection<Employee> allDep(){
        return employeeService.showAllDepartment();
    }


}
