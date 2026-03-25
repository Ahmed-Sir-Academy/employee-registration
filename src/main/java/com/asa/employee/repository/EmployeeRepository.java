package com.asa.employee.repository;

import com.asa.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    Optional<EmployeeEntity> findByNameAndDesignation(String name, String designation);

    Optional<EmployeeEntity> findByName(String name);
}
