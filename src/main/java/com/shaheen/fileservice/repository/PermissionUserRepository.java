package com.shaheen.fileservice.repository;


import com.shaheen.fileservice.model.PermissionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionUserRepository extends JpaRepository<PermissionUser,Integer> {
}
