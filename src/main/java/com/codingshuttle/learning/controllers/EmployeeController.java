package com.codingshuttle.learning.controllers;


import com.codingshuttle.learning.entities.EmployeeEntity;
import com.codingshuttle.learning.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    // TODO: Never Expose Entity and Repository inside Controller, Controller handles only DTO's, expose them in service
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    @GetMapping(path = "/{employeeId}")
//    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {
//        return new EmployeeDTO(id, "Utkarsh", "utk@gmail.com", 25, LocalDate.of(2026,5, 28),true);
//    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

//    @GetMapping
//    public String getAllEmployees(@RequestParam(required = false) Integer age,
//                                  @RequestParam(required = false) String sortBy) {
//        return "Hi" +" "+ age +" " +sortBy;
//    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy) {
        return employeeRepository.findAll();
    }

//    @PostMapping
//    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        employeeDTO.setId(121L);
//        return employeeDTO;
//    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }
}
