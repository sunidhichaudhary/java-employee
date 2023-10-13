package com.example.rqchallenge.employeeController.impl;

import com.example.rqchallenge.models.pojo.Employee;
import com.example.rqchallenge.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.rqchallenge.employees.IEmployeeController;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController implements IEmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/{searchString}")
    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString) {
        List<Employee> employees = employeeService.getEmployeesByNameSearch(searchString);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/highestSalary")
    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        Integer highestSalary = employeeService.getHighestSalaryOfEmployees();
        return ResponseEntity.ok(highestSalary);
    }

    @GetMapping("/topTenHighestEarningEmployeeNames")
    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        List<String> topEmployees = employeeService.getTopTenHighestEarningEmployeeNames();
        return ResponseEntity.ok(topEmployees);
    }

    @PostMapping
    @Override
    public ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> employeeInput) {
        Employee createdEmployee = employeeService.createEmployee(employeeInput);
        return ResponseEntity.ok(createdEmployee);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) {
        String deletedEmployeeName = employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok(deletedEmployeeName);
    }
}
