package com.example.rqchallenge.models.pojo;

import lombok.Data;
@Data
public class Employee {
    private String id;
    private String employee_name;
    private int employee_salary;
    private String employee_age;
    private String profile_image;

    public Employee() {
    }

    public Employee(String id, String employee_name, int employee_salary, String employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employee_name;
    }

    public void setEmployeeName(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getEmployeeSalary() {
        return employee_salary;
    }

    public void setEmployeeSalary(int employee_salary) {
        this.employee_salary = employee_salary;
    }

    public String getEmployeeAge() {
        return employee_age;
    }

    public void setEmployeeAge(String employee_age) {
        this.employee_age = employee_age;
    }

    public String getProfileImage() {
        return profile_image;
    }

    public void setProfileImage(String profile_image) {
        this.profile_image = profile_image;
    }
}
