package com.zack.booking.controller;

import com.zack.booking.dto.ResourceUpdateDto;
import com.zack.booking.exception.ResourceNotFoundException;
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
    public ResponseEntity<Resource> addResource(@RequestBody Resource resource) {

        if (resource.getName() == null || resource.getName().isEmpty()) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }

        // Check for duplicate
        if (service.existsByName(resource.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }

        Resource saved = service.addResource(resource);
        return new ResponseEntity<>(saved, HttpStatus.CREATED); // 201 Created
    }

    @GetMapping
    public ResponseEntity<List<Resource>> getAllResources() {
        List<Resource> resources = service.getAllResources();

        if (resources.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        try {
            Resource resource = service.getResourceByID(id);
            return new ResponseEntity<>(resource, HttpStatus.OK); // 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // 404 Not Found
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // 500 Internal Server Error
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable Long id,
                                                   @RequestBody ResourceUpdateDto dto) {
        try {
            System.out.println(dto.getType());
            Resource resource = service.updateResource(id, dto);
            return new ResponseEntity<>(resource, HttpStatus.OK); // 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResource(@PathVariable Long id) {
        try {
            service.deleteResource(id);
            return new ResponseEntity<>("Resource has deleted", HttpStatus.OK); // 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Not found"); // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // 500 Internal Server Error
        }
    }


}
