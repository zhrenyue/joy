package com.kvc.joy.plugin.security.erbac.model.po;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity;
import com.kvc.joy.plugin.security.erbac.support.enums.ResourceType;

@Entity
@Table(name = "T_ERBAC_AUTHORITY")
public class TErbacAuthority extends UuidCrudEntity {

	private String parentId; // 父权限id
	private String name; // 权限名称
	private String desc; // 权限描述
	private String resource; // 资源
	private ResourceType resourceType; 
	private String domain; // 资源(某一类资源)
	private String action; // 操作
	private String instance; // 资源实例(某类资源特定的实例)
	private Collection<TErbacUserAuth> userAuths; // 用户权限关系
	private Collection<TErbacRoleAuth> roleAuths; // 角色权限关系
	private Collection<TErbacGroupAuth> groupAuths; // 组权限关系
//	private Collection<TErbacRole> roles; // 角色
//	private Collection<TErbacUser> users; // 用户
//	private Collection<TErbacGroup> groups; // 组

	@Column(name="AUTH_NAME", length=64, nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	public String getDesc() {
		return desc;
	}

	@Column
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
//	@ManyToMany(cascade = CascadeType.REFRESH,
//			mappedBy="authorities", 
//			fetch = FetchType.LAZY
//			)
//	public Collection<TErbacRole> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Collection<TErbacRole> tErbacRoles) {
//		this.roles = tErbacRoles;
//	}

//	@ManyToMany(cascade = CascadeType.REFRESH)
//	@JoinTable(name = "T_ERBAC_AUTH_RES",
//			inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID"),
//			joinColumns = @JoinColumn(name = "AUTHORITY_ID"))	
//	public Collection<TErbacResource> getResources() {
//		return resources;
//	}
//
//	public void setResources(Collection<TErbacResource> tErbacResources) {
//		this.resources = tErbacResources;
//	}

//	@ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "authorities",// 通过维护端的属性关联
//			fetch = FetchType.LAZY)
//	public Collection<TErbacUser> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Collection<TErbacUser> users) {
//		this.users = users;
//	}
	
//	@ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "authorities",// 通过维护端的属性关联
//			fetch = FetchType.LAZY)
//	public Collection<TErbacGroup> getGroups() {
//		return groups;
//	}
//
//	public void setGroups(Collection<TErbacGroup> groups) {
//		this.groups = groups;
//	}

	@Column(length = 32)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@OneToMany(mappedBy = "authority", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public Collection<TErbacUserAuth> getUserAuths() {
		return userAuths;
	}

	public void setUserAuths(Collection<TErbacUserAuth> userAuths) {
		this.userAuths = userAuths;
	}

	@OneToMany(mappedBy = "authority", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public Collection<TErbacRoleAuth> getRoleAuths() {
		return roleAuths;
	}

	public void setRoleAuths(Collection<TErbacRoleAuth> roleAuths) {
		this.roleAuths = roleAuths;
	}
	
	@OneToMany(mappedBy = "authority", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public Collection<TErbacGroupAuth> getGroupAuths() {
		return groupAuths;
	}

	public void setGroupAuths(Collection<TErbacGroupAuth> groupAuths) {
		this.groupAuths = groupAuths;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(length = 32)
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(length = 32)
	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}
	
	@Column(length = 2, nullable = false)
	public String getResourceTypeCode() {
		return resourceType == null ? null : resourceType.getCode();
	}

	public void setResourceTypeCode(String typeCode) {
		this.resourceType = ResourceType.enumOf(typeCode);
	}

	@Transient
	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	@Column(nullable = false)
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
}
