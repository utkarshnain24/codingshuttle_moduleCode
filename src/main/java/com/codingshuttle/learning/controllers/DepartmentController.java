package com.codingshuttle.learning.controllers;

import com.codingshuttle.learning.dto.DepartmentDTO;
import com.codingshuttle.learning.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long departmentId) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(departmentId);
        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
        return new ResponseEntity<>(departmentService.createDepartment(departmentDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Long departmentId) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentDTO, departmentId));
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId) {
        boolean isDeleted = departmentService.deleteDepartmentById(departmentId);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}
