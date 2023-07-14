package com.stc.assessments.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.assessments.model.Permissions;

public interface PermissionsRepository extends JpaRepository<Permissions, Integer> {

	Optional<Permissions> findByUserName(String user_name);

}
