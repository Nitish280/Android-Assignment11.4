package com.example.lenovo.android114;

/**
 * Created by lenovo on 8/17/2017.
 */

public class Employee {
    //declaration
    int id;
    String employeeFirstName;
    String employeeLastName;
    String employeeAge;
    String employeeSex;

    public Employee(){

    }
    //creating Constructor
    public Employee(int id, String employeeFirstName, String employeeLastName, String employeeAge, String employeeSex){
        this.id = id;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeAge = employeeAge;
        this.employeeSex = employeeSex;
    }
    //Using getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(String employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(String employeeSex) {
        this.employeeSex = employeeSex;
    }
}


