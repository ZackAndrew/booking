package com.zack.booking.repository;

import com.zack.booking.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Long> {
    boolean existsByName(String name);
}
