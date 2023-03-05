package com.example.lists.service;

import com.example.lists.exception.EmployeeAlreadyAddedException;
import com.example.lists.exception.EmployeeNotFoundException;
import com.example.lists.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    //КОЛ-ВО СОТРУДНИКОВ В МАССИВЕ
    private static int size;
    //ОБЪЯВЛНИЕ ЛИСТА
    static List<Employee> employeeList = new ArrayList<Employee>();

    //МЕТОД УДАЛЕНИЯ СОТРУДНИКА ИЗ МАССИВА
    public static String delEmployee(String firstName, String lastName) {
        boolean test = false;
        for (int i = 0; i < size; i++) {
            if (employeeList.get(i).getFirstName().equals(firstName) && employeeList.get(i).getLastName().equals(lastName)) {
                test = true;
                employeeList.set(i, null);
                if (i == size - 1) {
                    size--;
                    break;
                } else {
                    for (int j = i; j < size - 1; j++) {
                        employeeList.set(j, employeeList.get(j + 1));
                    }
                    size--;
                    break;
                }
            }
        }
        if (test == false) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            return "Сотрудник удалён";
        }

    }

    //МЕТОД ДОБАВЛЕНИЯ СОТРУДНИКА ИЗ МАССИВА
    public static Employee addEmployee(String firstName, String lastName) {
        boolean test = false;

        if (size >= 10) {
            throw new EmployeeStorageIsFullException("Массив переполнен");
        }
        for (int i = 0; i < size; i++) {

            if (employeeList.get(i).getFirstName().equals(firstName) && employeeList.get(i).getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Такой сотрдник уже есть");
            }
        }
        employeeList.add(size++, new Employee(firstName, lastName));
        return new Employee(firstName,lastName);


    }

    //МЕТОД ПОИСКА СОТРУДНИКА В МАССИВА
    public static Employee searchArr(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)){
            return employee;
        }else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public static List<Employee> showAllArr() {
        StringBuilder arrStringBuilder = new StringBuilder("");
        for (int i = 0; i < size; i++) {
            arrStringBuilder.append(employeeList.get(i).toString() + "\n");
        }

        String arrString = String.valueOf(arrStringBuilder);
        if (arrString.isBlank()) {
            throw new EmployeeNotFoundException("Сотрудники не найдены");
        }
        return employeeList;
    }
}
