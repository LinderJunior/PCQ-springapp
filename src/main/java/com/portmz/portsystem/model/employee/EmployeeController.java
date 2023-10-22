package com.portmz.portsystem.model.employee;
import java.net.URI;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<EmployeeDto> findAllEmployee(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<Object> getByIdEmployee(@PathVariable Long id) {
        Optional<Employee> resourceOptional = Optional.ofNullable(employeeService.getEmployeeByIds(id));
        return ResponseEntity.status(HttpStatus.OK).body(resourceOptional.get());
    }


    @PostMapping
    public ResponseEntity<Object>saveEmployee(@Valid @RequestBody EmployeeDto dto){
        dto = employeeService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteByIdEmployee(@PathVariable Long id) {
        Optional<Employee> resourceOptional = Optional.ofNullable(employeeService.getEmployeeByIds(id));
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successffully");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public ResponseEntity<Object>updateResources(@PathVariable Long id, @RequestBody EmployeeDto dto){
        Optional<Employee> resourceOptional = Optional.ofNullable(employeeService.getEmployeeByIds(id));
        Employee employee = employeeService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
}
