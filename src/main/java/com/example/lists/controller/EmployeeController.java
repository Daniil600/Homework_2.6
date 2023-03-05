package com.example.lists.controller;

import com.example.lists.exception.EmployeeAlreadyAddedException;
import com.example.lists.exception.EmployeeNotFoundException;
import com.example.lists.exception.EmployeeStorageIsFullException;
import com.example.lists.service.EmployeeService;
import com.example.lists.service.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/employee")
@RestController
public class EmployeeController {
    //ResponseStatus мы объявляем для вывода определённый ошибки в случаи возниконовения исключания
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // ExceptionHandler обрабатывает исключение и выводит ResponseStatus объявленный выше в случаи вознокновения ошибки: "EmployeeStorageIsFullException"
    @ExceptionHandler(EmployeeStorageIsFullException.class)
    //Вывод сообщения ошибки
    public String handleException(EmployeeStorageIsFullException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String handleException(EmployeeAlreadyAddedException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleException(EmployeeNotFoundException e) {
        return String.format("%s %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public List<Employee> hello(){
        return EmployeeService.showAllArr();
    }
    @GetMapping(path = "/add")
    public Employee add(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return EmployeeService.addEmployee(firstName, lastName);
    }
    @GetMapping(path = "/remove")
    public String del(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return EmployeeService.delEmployee(firstName,lastName);
    }
    @GetMapping(path = "/find")
    public Employee find(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        return EmployeeService.searchArr(firstName,lastName);
    }

}
