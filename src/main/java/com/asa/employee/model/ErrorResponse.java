package com.asa.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private int errorCode;
    private String message;
    private long milliSeconds;
}
