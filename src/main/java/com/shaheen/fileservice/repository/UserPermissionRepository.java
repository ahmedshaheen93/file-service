package com.shaheen.fileservice.repository;


import com.shaheen.fileservice.model.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission,Integer> {
}
