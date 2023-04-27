package com.shaheen.fileservice.repository;


import com.shaheen.fileservice.model.GroupPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPermissionRepository extends JpaRepository<GroupPermission,Integer> {
}
