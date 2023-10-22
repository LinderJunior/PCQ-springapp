package com.portmz.portsystem.model.employee;

import java.util.List;


public interface EmployeeService {


    List<EmployeeDto> findAll();

    Employee getEmployeeByIds(Long id);

    EmployeeDto  save(EmployeeDto dto);

    void delete(Long id);

    Employee update(Long id, EmployeeDto dto);
}
