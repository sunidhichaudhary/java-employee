package com.example.rqchallenge;

import com.example.rqchallenge.models.pojo.Employee;
import com.example.rqchallenge.response.ApiResponse;
import com.example.rqchallenge.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplateBuilder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        employeeService = new EmployeeService(restTemplateBuilder.build());
    }

    @Test
    public void testGetAllEmployees_Success() {
        ResponseEntity<ApiResponse<List<Employee>>> responseEntity = new ResponseEntity<>(
                new ApiResponse<>("success", Collections.singletonList(new Employee())),
                HttpStatus.OK
        );
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(ParameterizedTypeReference.class))
                .thenReturn(responseEntity);

        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(1, employees.size());
        // Add more assertions as needed
    }

    @Test
    public void testGetAllEmployees_Error() {
        ResponseEntity<ApiResponse<List<Employee>>> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(ParameterizedTypeReference.class))
                .thenReturn(responseEntity);

        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(0, employees.size());

    }
}

