package com.stc.assessments.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class PermissionGroups {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String groupName;
	@JsonIgnore
	@OneToMany(mappedBy = "permissionGroupId")
	private Set<Item> items;

	PermissionGroups() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public PermissionGroups(Integer id, String groupName, Set<Item> items) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.items = items;
	}

	public PermissionGroups(String groupName) {
		super();
		this.groupName = groupName;
	}

	public PermissionGroups(Integer id) {
		this.id = id;
	}

}
