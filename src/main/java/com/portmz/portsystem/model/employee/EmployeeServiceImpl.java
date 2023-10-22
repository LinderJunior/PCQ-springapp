package com.portmz.portsystem.model.employee;

import com.portmz.portsystem.exceptionHandler.BusinesException;
import com.portmz.portsystem.model.user.User;
import com.portmz.portsystem.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService  {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> list = employeeRepository.findAll();
        return list.stream().map(x-> new EmployeeDto(x)).collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeByIds(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new BusinesException("Employee with ID " + id + " not found"));
    }


    @Override
    public EmployeeDto  save(EmployeeDto dto) {

        User userSpringSecurity = userService.authenticated();
        if (userSpringSecurity == null) {
            throw new RuntimeException("Usuário não autenticado.");
        }
        // Verifique se o objeto user tem authorities antes de acessá-las
        if (userSpringSecurity.getAuthorities() == null) {
            throw new RuntimeException("O usuário não possui autorizações.");
        }
        Employee employee = new Employee();
        if(employeeRepository.existsByEmail(dto.getEmail())){
            throw new BusinesException("A email with the same name already exists INSERT ");
        }
        copyDtoToEntityInsert(employee, dto);
        employee =employeeRepository.save(employee);
        return new EmployeeDto(employee);
    }

    @Override
    public void delete(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public Employee update(Long id, EmployeeDto dto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (!employee.getEmail().equals(dto.getEmail()) && employeeRepository.existsByEmail(dto.getEmail())) {
                throw new BusinesException("A email with the same name already exist UPDATE ");
            }
            copyDtoToEntityInsert(employee, dto);
            return employeeRepository.save(employee);
        }
        return null;
    }

    private void copyDtoToEntityInsert(Employee employee, EmployeeDto dto) {
        employee.setCell(dto.getCell());
        employee.setEmail(dto.getEmail());
        employee.setLastName(dto.getLastName());
        employee.setFirstName(dto.getFirstName());
        employee.setGender(dto.getGender());
        employee.setDateBirth(dto.getDateBirth());
    }

}
