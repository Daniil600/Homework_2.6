package com.example.lists.service;

import com.example.lists.exception.EmployeeAlreadyAddedException;
import com.example.lists.exception.EmployeeNotFoundException;
import com.example.lists.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpArr {
    //КОЛ-ВО СОТРУДНИКОВ В МАССИВЕ
    private static int size;
    //ОБЪЯВЛНИЕ ЛИСТА
    static List<Employee> employeeList = new ArrayList<>(List.of());

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
    public static String addEmployee(String firstName, String lastName) {
        boolean test = false;

        if (size >= 10) {
            throw new EmployeeStorageIsFullException("Массив переполнен");
        }
        for (int i = 0; i < size + 1; i++) {

            if (employeeList.get(i).getFirstName().equals(firstName) && employeeList.get(i).getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Такой сотрдник уже есть");
            }
        }
        employeeList.set(size++, new Employee(firstName, lastName));
        return "Сотрудник добавлен";


    }

    //МЕТОД ПОИСКА СОТРУДНИКА В МАССИВА
    public static String searchArr(String firstName, String lastName) {
        boolean test = false;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (employeeList.get(i).getFirstName().equals(firstName) && employeeList.get(i).getLastName().equals(lastName)) {
                index = i;
                break;
            }
        }
        if (test == false) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            return employeeList.get(index).getFirstName() + " " + employeeList.get(index).getLastName();
        }
    }

    public static String showAllArr() {
        StringBuilder arrStringBuilder = new StringBuilder("");
        for (int i = 0; i < size; i++) {
            arrStringBuilder.append(employeeList.get(i).getFirstName() + " " + employeeList.get(i).getLastName() + "\n");
        }

        String arrString = String.valueOf(arrStringBuilder);
        if (arrString.isBlank()) {
            return "Список пуст";
        }
        return arrString;
    }
}
