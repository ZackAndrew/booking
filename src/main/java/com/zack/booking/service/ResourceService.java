package com.zack.booking.service;

import com.zack.booking.exception.ResourceAlreadyExistsException;
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
}
