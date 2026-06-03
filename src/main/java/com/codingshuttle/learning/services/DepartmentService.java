package com.codingshuttle.learning.services;

import com.codingshuttle.learning.dto.DepartmentDTO;
import com.codingshuttle.learning.entities.DepartmentEntity;
import com.codingshuttle.learning.exceptions.ResourceNotFoundException;
import com.codingshuttle.learning.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    private void isExitsByDepartmentId(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if (!exists) throw new ResourceNotFoundException("No Department with id: " + departmentId + " exists");
    }

    public Optional<DepartmentDTO> getDepartmentById(Long departmentId) {
        return departmentRepository
                .findById(departmentId)
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository
                .findAll()
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .toList();
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setCreatedAt(LocalDateTime.now());
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Long departmentId) {
        isExitsByDepartmentId(departmentId);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        Optional<DepartmentEntity> foundDepartmentEntity = departmentRepository.findById(departmentId);
        LocalDateTime createdAt = foundDepartmentEntity.get().getCreatedAt();
        departmentEntity.setId(departmentId);
        departmentEntity.setCreatedAt(createdAt);
        departmentEntity.setUpdatedAt(LocalDateTime.now());
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);

    }

    public boolean deleteDepartmentById(Long departmentId) {
        isExitsByDepartmentId(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }
}
