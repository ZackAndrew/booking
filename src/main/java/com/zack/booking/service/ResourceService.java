package com.zack.booking.service;

import com.zack.booking.dto.ResourceUpdateDto;
import com.zack.booking.exception.ResourceAlreadyExistsException;
import com.zack.booking.exception.ResourceNotFoundException;
import com.zack.booking.model.Resource;
import com.zack.booking.repository.ResourceRepo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepo resourceRepo;

    public ResourceService(ResourceRepo resourceRepo) {
        this.resourceRepo = resourceRepo;
    }

    public boolean existsByName(String name) {
        return resourceRepo.existsByName(name);
    }

    public Resource addResource(Resource resource) {

        if (resource.getName() == null || resource.getName().isEmpty()) {
            throw new IllegalArgumentException("Resource name cannot be empty");
        }

        if (existsByName(resource.getName())) {
            throw new ResourceAlreadyExistsException("Resource with this name already exists");
        }
        return resourceRepo.save(resource);
    }

    public List<Resource> getAllResources() {
        return resourceRepo.findAll();
    }

    public Resource getResourceByID(Long id) {
        return resourceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public Resource updateResource(Long id, ResourceUpdateDto dto) {
        Resource existing = resourceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        if (dto.getName() != null) {
            if (dto.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty or blank");
            }
            existing.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            existing.setDescription(dto.getDescription().trim());
        }

        if ((dto.getType() != null)){
           existing.setType(dto.getType());
        }

        return resourceRepo.save(existing);
    }

    public void deleteResource(Long id) {

        if(!resourceRepo.existsById(id)){
            throw new ResourceNotFoundException("Resource not found");
        }
        resourceRepo.deleteById(id);
    }
}
