package com.asa.employee.service;

import com.asa.employee.entity.EmployeeEntity;
import com.asa.employee.exceptions.InvalidRequestException;
import com.asa.employee.exceptions.NotFoundException;
import com.asa.employee.interfaces.EmployeeInterface;
import com.asa.employee.model.Employee;
import com.asa.employee.model.EmployeeResponse;
import com.asa.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService implements EmployeeInterface {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<?> registerEmployee(Employee employee) {

        validateEmployeeObject(employee);

        EmployeeEntity employeeEntity = convertModelToEntity(employee);

        Optional<EmployeeEntity> byNameAndDesignation =
                employeeRepository.findByNameAndDesignation(employee.getName(), employee.getDesignation());

        if(byNameAndDesignation.isPresent()){
            throw new InvalidRequestException("Already record is present with employee Name and Designation with id "+ byNameAndDesignation.get().getId());
        }

        employeeRepository.save(employeeEntity);

        return new ResponseEntity<>(new EmployeeResponse("Employee Registered Successfully",null, HttpStatusCode.valueOf(200).value()), HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<?> findEmployeeByName(String name) {
        if(name == null || name.isEmpty()){
            throw new InvalidRequestException("Name cannot be empty");
        }

        Optional<EmployeeEntity> byName = employeeRepository.findByName(name);

        if(byName.isPresent()){
            return new ResponseEntity<>(new EmployeeResponse("Employee Found Successfully",byName.get(), HttpStatusCode.valueOf(200).value()), HttpStatusCode.valueOf(200));
        }else{
            throw new NotFoundException("No employee found with name "+name);
        }
    }

    private EmployeeEntity convertModelToEntity(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setDesignation(employee.getDesignation());
        employeeEntity.setName(employee.getName());
        employeeEntity.setSalary(employee.getSalary());

        return employeeEntity;
    }

    private void validateEmployeeObject(Employee employee) {
        if (employee == null || employee.getDesignation() == null || employee.getName() == null || employee.getSalary() == 0) {
            throw new InvalidRequestException("Employee Designation or Name or Salry cannot be Null or Empty");
        }
    }
}
