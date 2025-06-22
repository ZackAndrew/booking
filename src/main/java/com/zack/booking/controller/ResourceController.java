package com.zack.booking.controller;

import com.zack.booking.model.Resource;
import com.zack.booking.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Resource> addResource(@RequestBody Resource resource){

        if (resource.getName() == null || resource.getName().isEmpty()){
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }

        // Check for duplicate
        if(service.existsByName(resource.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }

        Resource saved = service.addResource(resource);
        return new ResponseEntity<>(saved, HttpStatus.CREATED); // 201 Created
    }

    @GetMapping
    public ResponseEntity<List<Resource>> getAllResources(){
        List<Resource> resources = service.getAllResources();

        if (resources.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resources);
    }
}
