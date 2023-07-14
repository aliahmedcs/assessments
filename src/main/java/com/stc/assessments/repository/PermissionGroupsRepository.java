package com.stc.assessments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.assessments.model.PermissionGroups;

public interface PermissionGroupsRepository extends JpaRepository<PermissionGroups, Integer> {

}
