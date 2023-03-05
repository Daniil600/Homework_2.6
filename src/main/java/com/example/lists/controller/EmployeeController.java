package com.example.lists.controller;

import com.example.lists.service.EmployeeService;
import com.example.lists.object.Employee;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping
    public Collection<Employee> hello(){
        return employeeService.showAllArr();
    }
    @GetMapping(path = "/add")
    public Employee add(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return employeeService.addEmployee(firstName, lastName);
    }
    @GetMapping(path = "/remove")
    public Employee del(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return employeeService.delEmployee(firstName,lastName);
    }
    @GetMapping(path = "/find")
    public Employee find(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return employeeService.find(firstName,lastName);
    }

}
