package com.asa.employee.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee {
    private String name;
    private String designation;
    private Integer salary;
}
