package com.stc.assessments.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.stc.assessments.enumeration.PermissionLevel;

@Entity

public class Permissions implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String userEmail;
	@javax.persistence.Enumerated(EnumType.STRING)

	private PermissionLevel permissionLevel;
	@Column(columnDefinition = "text")
	private String password;
	@Column(columnDefinition = "text")
	private String userName;

	@ManyToMany
	@JoinTable(name = "premmations_groups", joinColumns = @JoinColumn(name = "permissions_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "groups_id", referencedColumnName = "id"))
	private Set<PermissionGroups> permissionGroups = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public PermissionLevel getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(PermissionLevel permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	public String getUserName() {
		return userName;
	}

	

	public void addPermissionGroup(PermissionGroups permissionGroups) {
		this.permissionGroups.add(permissionGroups);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (PermissionGroups role : permissionGroups) {
			authorities.add(new SimpleGrantedAuthority(role.getGroupName()));
		}
		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+authorities.get(0)+authorities.size());
		return authorities;
	}

	public Set<PermissionGroups> getPermissionGroups() {
		return permissionGroups;
	}

	public void setPermissionGroups(Set<PermissionGroups> permissionGroups) {
		this.permissionGroups = permissionGroups;
	}

	public void addPermissionGroups(PermissionGroups permissionGroups) {
		this.permissionGroups.add(permissionGroups);
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Permissions(String email, String password) {
		this.userName = email;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userEmail;
	}

	public Permissions() {
	}

}