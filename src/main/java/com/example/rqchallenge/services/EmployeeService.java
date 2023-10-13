package com.example.rqchallenge.services;
import com.example.rqchallenge.models.pojo.Employee;
import com.example.rqchallenge.response.ApiResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class EmployeeService {
    private final String baseUrl = "https://dummy.restapiexample.com/api/v1";
    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final RestTemplate restTemplate;

    public EmployeeService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Employee> getAllEmployees() {
        try {
            ResponseEntity<ApiResponse<List<Employee>>> response = restTemplate.exchange(
                    baseUrl + "/employees",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<List<Employee>>>() {
                    }
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody().getData();
            } else {
                logger.error("Error while fetching all employees. Status code: {}", response.getStatusCode());

                return Collections.emptyList();
            }
        }catch (Exception e) {
            logger.error("Error while fetching all employees", e);
            return Collections.emptyList();
        }
    }


    public List<Employee> getEmployeesByNameSearch(String searchString) {
        try {

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/employees")
                    .queryParam("search", searchString);

            ResponseEntity<ApiResponse<List<Employee>>> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<List<Employee>>>() {
                    }
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody().getData();
            } else {
                logger.error("Error while fetching name of employees. Status code: {}", response.getStatusCode());
                return Arrays.asList();
            }
        }catch (Exception e) {
            logger.error("Error while fetching all employees by Name", e);
            return Collections.emptyList();
        }
    }

    public Employee getEmployeeById(String id) {
        try{
        ResponseEntity<ApiResponse<Employee>> response = restTemplate.exchange(
                baseUrl + "/employee/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Employee>>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getData();
        } else {
            logger.error("Error while fetching employee by Id Status code: {}", response.getStatusCode());
            return null;
        }
        }catch (Exception e) {
            logger.error("Error while fetching  employee by Id ", e);
            return null;
        }
    }

    public int getHighestSalaryOfEmployees() {
        try{
        ResponseEntity<ApiResponse<List<Employee>>> response = restTemplate.exchange(
                baseUrl + "/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Employee>>>(){}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            List<Employee> employees = response.getBody().getData();
            int highestSalary = employees.stream()
                    .map(Employee::getEmployeeSalary)
                    .max(Integer::compareTo)
                    .orElse(0);
            return highestSalary;
        } else {
            logger.error("Error while fetching highest salary of employee. Status code: {}", response.getStatusCode());
            return 0;
        } }catch (Exception e) {
            logger.error("Error while fetching highest salary  employees", e);
            return 0;
        }
    }
    public List<String> getTopTenHighestEarningEmployeeNames() {
        try{
        ResponseEntity<ApiResponse<List<Employee>>> response = restTemplate.exchange(
                baseUrl + "/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<List<Employee>>>(){}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            List<Employee> employees = response.getBody().getData();
            List<String> topTenNames = employees.stream()
                    .sorted((e1, e2) -> Integer.compare(e2.getEmployeeSalary(), e1.getEmployeeSalary()))
                    .limit(10)
                    .map(Employee::getEmployeeName)
                    .toList();
            return topTenNames;
        } else {
            logger.error("Error while fetching TopTenHighestEarningEmployeeNames . Status code: {}", response.getStatusCode());
            return Arrays.asList();
        } }catch (Exception e) {
            logger.error("Error while fetching TopTenHighestEarningEmployeeNames ", e);
            return Collections.emptyList();
        }
    }

    public Employee createEmployee(Map<String, Object> employeeInput) {
        try{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(employeeInput, headers);

        ResponseEntity<ApiResponse<Employee>> response = restTemplate.exchange(
                baseUrl + "/create",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<ApiResponse<Employee>>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getData();
        } else {
            logger.error("Error while creating employees. Status code: {}", response.getStatusCode());
            return null;
        } }catch (Exception e) {
            logger.error("Error while creating employees", e);
            return null;
        }
    }

    public String deleteEmployeeById(String id) {
        try{
        ResponseEntity<ApiResponse<String>> response = restTemplate.exchange(
                baseUrl + "/delete/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<ApiResponse<String>>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getData();
        } else {
            logger.error("Error while deleting employee. Status code: {}", response.getStatusCode());
            return null;
        } }catch (Exception e) {
        logger.error("Error while deleting employees", e);
        return null;
    }
    }
}
