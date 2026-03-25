package com.asa.employee.service;

import com.asa.employee.entity.EmployeeEntity;
import com.asa.employee.exceptions.InvalidRequestException;
import com.asa.employee.model.Employee;
import com.asa.employee.model.EmployeeResponse;
import com.asa.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void testRegisterEmployee_Success(){
        Employee employee = new Employee();
        employee.setName("Priti");
        employee.setDesignation("Software Developer");
        employee.setSalary(100000);

        when(employeeRepository.findByNameAndDesignation(anyString(), anyString())).thenReturn(Optional.empty());

        ResponseEntity<EmployeeResponse> responseEntity = employeeService.registerEmployee(employee);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals("Employee Registered Successfully",responseEntity.getBody().getMessage());
    }

    @Test
    void testRegisterEmployee_Bad_Request(){
        Employee employee = new Employee();
        employee.setName("Priti");
        employee.setDesignation("Software Developer");
        employee.setSalary(100000);


        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("Priti");
        employeeEntity.setDesignation("Software Developer");
        employeeEntity.setSalary(100000);

        Optional<EmployeeEntity> employeeEntityClass = Optional.of(employeeEntity);

        when(employeeRepository.findByNameAndDesignation(anyString(), anyString())).thenReturn(employeeEntityClass);

        try {
            employeeService.registerEmployee(employee);
        }catch (InvalidRequestException ex){
            assertEquals("Already record is present with employee Name and Designation with id 1", ex.getMessage());
        }
    }
}
