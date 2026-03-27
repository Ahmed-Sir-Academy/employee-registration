package com.asa.employee.service;

import com.asa.employee.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@Slf4j
public class EmployeeSchedular {

    @Value("${spring.scheduler.flag:false}")
    private boolean flag;

    @Autowired
    EmployeeService employeeService;

    @Scheduled(cron = "0 * * * * *")
    public void registerEmployeeSchedular(){
        if(flag) {
            LocalDateTime dateTime = LocalDateTime.now();
            log.info("Schedular started at {}", dateTime);

            Random random = new Random();
            Employee employee = new Employee();

            String[] names = {"John Doe", "Jane Smith", "Alice Brown", "Bob White"};
            employee.setName(names[random.nextInt(names.length)]);

            // 2. Random Designation
            String[] designations = {"Developer", "Manager", "Designer", "QA Engineer"};
            employee.setDesignation(designations[random.nextInt(designations.length)]);

            // 3. Random Salary (e.g., between 30000 and 100000)
            int randomSalary = 30000 + (100000 - 30000) * random.nextInt();
            employee.setSalary(randomSalary);

            log.info("Data inserted is : {}", employee.toString());
            employeeService.registerEmployee(employee);

            LocalDateTime endTime = LocalDateTime.now();
            log.info("Schedular ended at {}", endTime);
        }
    }

}
