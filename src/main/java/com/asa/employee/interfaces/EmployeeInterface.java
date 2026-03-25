package com.asa.employee.interfaces;

import com.asa.employee.model.Employee;
import org.springframework.http.ResponseEntity;

public interface EmployeeInterface {
    ResponseEntity<?> registerEmployee(Employee employee);

    ResponseEntity<?> findEmployeeByName(String name);
}
