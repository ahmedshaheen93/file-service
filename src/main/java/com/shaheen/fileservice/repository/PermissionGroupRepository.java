package com.shaheen.fileservice.repository;


import com.shaheen.fileservice.model.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup,Integer> {
}
