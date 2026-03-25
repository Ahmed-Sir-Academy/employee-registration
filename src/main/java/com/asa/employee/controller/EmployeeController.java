package com.asa.employee.controller;

import com.asa.employee.interfaces.EmployeeInterface;
import com.asa.employee.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeInterface employeeInterface;

    @PostMapping("registerEmployee")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {
        return employeeInterface.registerEmployee(employee);
    }

    @GetMapping("findEmployee/{name}")
    public ResponseEntity<?> registerEmployee(@PathVariable String name) {
        return employeeInterface.findEmployeeByName(name);
    }

}
