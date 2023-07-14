package com.stc.assessments.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stc.assessments.model.Files;

public interface FilesRepository extends JpaRepository<Files, Integer> {

	@Query(value = "SELECT DISTINCT(f.*) from files f,item i,premmations_groups psgs, permissions p where f.item_id=i.id and i.group_id=psgs.groups_id and psgs.permissions_id=p.id and f.id=15 and p.permission_level='EDIT'", nativeQuery = true)
	Files getMetaData(@Param(value = "fileId") int fileId);

}
