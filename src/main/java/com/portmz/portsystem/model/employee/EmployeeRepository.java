package com.portmz.portsystem.model.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    boolean existsByEmail(String email);

    List<EmployeeDto> findEmployeeById(Long employeeId);
}
