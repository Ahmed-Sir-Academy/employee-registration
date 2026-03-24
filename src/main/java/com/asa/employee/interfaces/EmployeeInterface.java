package com.asa.employee.interfaces;

import com.asa.employee.model.Employee;
import org.springframework.http.ResponseEntity;

public interface EmployeeInterface {
    public ResponseEntity<?> registerEmployee(Employee employee);

}
