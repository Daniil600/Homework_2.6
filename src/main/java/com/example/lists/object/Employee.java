package com.example.lists.object;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private int sallary;
    private int departament;



    public Employee(String firstName, String lastName, int sallary, int departament) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sallary = sallary;
        this.departament = departament;
    }
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public int getSallary() {
        return sallary;
    }

    public int getDepartament() {
        return departament;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        if (employee.hashCode() != this.hashCode()){
            return false;
        }
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        int result1 = firstName == null ? 0 : firstName.hashCode();
        int result2 = lastName == null ? 0 : lastName.hashCode();
        int result = 31 * result1 + result2;
        return result;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

