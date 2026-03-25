package com.asa.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse {
    private String message;
    private Object body;
    private int statusCode;
}
