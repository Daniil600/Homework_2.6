package com.example.lists.controller;

import com.example.lists.service.EmpArr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @RequestMapping(path = "/employee")
    public String hello(){
        return EmpArr.showAllArr();
    }
    @GetMapping(path = "/add")
    public String add(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return EmpArr.addEmployee(firstName, lastName);
    }
    @GetMapping(path = "/remove")
    public String del(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return EmpArr.delEmployee(firstName,lastName);
    }
    @GetMapping(path = "/find")
    public String find(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return EmpArr.searchArr(firstName,lastName);
    }

}
