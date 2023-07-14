package com.stc.assessments.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stc.assessments.enumeration.EnumerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@javax.persistence.Enumerated(EnumType.STRING)
   
	private EnumerationType type;
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy="item")
	private Set<Files> files;
	
	@ManyToOne
	@JoinColumn(name="group_id", nullable=false)
	private PermissionGroups permissionGroupId;
}
