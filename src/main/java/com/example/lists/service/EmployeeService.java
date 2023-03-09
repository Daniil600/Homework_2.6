package com.example.lists.service;

import com.example.lists.exception.EmployeeAlreadyAddedException;
import com.example.lists.exception.EmployeeNotFoundException;
import com.example.lists.exception.EmployeeStorageIsFullException;
import com.example.lists.object.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final int MAX_COUNT_OF_EMPLOYEE = 10;
    Map<String, Employee> employees = new HashMap<>();

    //МЕТОД УДАЛЕНИЯ СОТРУДНИКА ИЗ МАССИВА
    public Employee delEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFirstName() + " " + employee.getLastName())) {
            employees.remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    //МЕТОД ДОБАВЛЕНИЯ СОТРУДНИКА ИЗ МАССИВА
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_COUNT_OF_EMPLOYEE) {
            throw new EmployeeStorageIsFullException("Массив переполнен");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFirstName() + " " + employee.getLastName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        employees.put(employee.getFirstName() + " " + employee.getLastName(), employee);
        return employee;


    }

    //МЕТОД ПОИСКА СОТРУДНИКА В МАССИВА
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFirstName() + " " + employee.getLastName())) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Collection<Employee> showAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
