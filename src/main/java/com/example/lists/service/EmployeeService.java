package com.example.lists.service;

import com.example.lists.exception.EmployeeAlreadyAddedException;
import com.example.lists.exception.EmployeeNotFoundException;
import com.example.lists.exception.EmployeeStorageIsFullException;
import com.example.lists.object.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private final int MAX_COUNT_OF_EMPLOYEE = 10;
    //КОЛ-ВО СОТРУДНИКОВ В МАССИВЕ
    private static int size;
    //ОБЪЯВЛНИЕ ЛИСТА
    List<Employee> employeeList = new ArrayList<Employee>();

    //МЕТОД УДАЛЕНИЯ СОТРУДНИКА ИЗ МАССИВА
    public Employee delEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if(employeeList.contains(employee)){
            employeeList.remove(employee);
            size--;
            return employee;
        }else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    //МЕТОД ДОБАВЛЕНИЯ СОТРУДНИКА ИЗ МАССИВА
    public Employee addEmployee(String firstName, String lastName) {
        if (size >= MAX_COUNT_OF_EMPLOYEE) {
            throw new EmployeeStorageIsFullException("Массив переполнен");
        }
        Employee employee = new Employee(firstName,lastName);
        if (employeeList.contains(employee)){
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        size++;

        employeeList.add(employee);

        return employee;


    }

    //МЕТОД ПОИСКА СОТРУДНИКА В МАССИВА
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)){
            return employee;
        }else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Collection<Employee> showAll() {
        return Collections.unmodifiableList(employeeList);
    }
}
