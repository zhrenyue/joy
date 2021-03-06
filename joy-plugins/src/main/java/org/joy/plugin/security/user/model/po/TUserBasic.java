package org.joy.plugin.security.user.model.po;

import org.joy.commons.enums.Sex;
import org.joy.core.persistence.orm.jpa.annotations.Comment;
import org.joy.core.persistence.orm.jpa.annotations.DefaultValue;
import org.joy.core.persistence.support.entity.UuidCrudEntity;
import org.joy.plugin.security.erbac.model.po.TErbacGroup;
import org.joy.plugin.security.erbac.model.po.TErbacRole;
import org.joy.plugin.security.erbac.model.po.TErbacUserAuth;
import org.joy.plugin.security.erbac.support.enums.UserStatus;

import javax.persistence.*;
import java.util.Collection;

/**
 * 用户基本信息实体
 *
 * @author Kevice
 * @time 14-2-16 上午10:44
 * @since 1.0.0
 */
@Entity
@Table(name = "t_user_basic")
@Comment("用户基础信息")
public class TUserBasic extends UuidCrudEntity  {

	private static final long serialVersionUID = -8994293458339301394L;
	public static final String HTTP_SESSION_USER_ID = "JOY_HTTP_SESSION_USER_ID";

	private String account; // 登陆帐号
	private String name; // 姓名
	private Sex sex; // 性别
	private String password; // 密码
	private UserStatus status; // 状态
	private String online; // 是否在线
	private Integer loginCount; // 登录次数
	private String lastLoginTime; // 最后一次登录时间
	private Collection<TErbacRole> roles; // 角色
	private Collection<TErbacGroup> groups; // 组
	// private Collection<TErbacAuthority> authorities; // 权限
	private Collection<TErbacUserAuth> userAuths; // 用户权限关系
	
	@Column(name = "USER_ACCOUNT", length = 32, nullable = false, unique = true)
	@Comment("账号")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "USER_NAME", length = 64)
	@Comment("姓名")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 32)
	@Comment("密码")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 2, nullable = false)
	@Comment(value="状态代码", codeId="user_status")
	public String getStatusCode() {
		return status == null ? null : status.getCode();
	}

	public void setStatusCode(String statusCode) {
		this.status = UserStatus.enumOf(statusCode);
	}

	@Transient
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@Column(length = 1, nullable = false)
	@Comment(value="性别代码", codeId="sex")
	public String getSexCode() {
		return sex == null ? null : sex.getCode();
	}

	public void setSexCode(String sexCode) {
		this.sex = Sex.enumOf(sexCode);
	}

	@Transient
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "users",// 通过维护端的属性关联
	fetch = FetchType.LAZY)
	public Collection<TErbacRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<TErbacRole> tErbacRoles) {
		this.roles = tErbacRoles;
	}

	// @ManyToMany(cascade = CascadeType.REFRESH)
	// @JoinTable(name = "t_erbac_user_auth", // 关联表名
	// inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID"),// 被维护端外键
	// joinColumns = @JoinColumn(name = "USER_ID")) // 维护端外键
	// @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch =
	// FetchType.LAZY)
	// public Collection<TErbacAuthority> getAuthorities() {
	// return authorities;
	// }
	//
	// public void setAuthorities(Collection<TErbacAuthority> authorities) {
	// this.authorities = authorities;
	// }

	@ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "users",// 通过维护端的属性关联
	fetch = FetchType.LAZY)
	public Collection<TErbacGroup> getGroups() {
		return groups;
	}

	public void setGroups(Collection<TErbacGroup> groups) {
		this.groups = groups;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public Collection<TErbacUserAuth> getUserAuths() {
		return userAuths;
	}

	public void setUserAuths(Collection<TErbacUserAuth> userAuths) {
		this.userAuths = userAuths;
	}

	@Column(length = 10)
	@Comment("登陆次数")
	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	@Column(length = 14)
	@Comment("最后登陆时间")
	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "ONLINE_STATUS", length = 1, nullable = false)
	@Comment("在线")
	@DefaultValue("0")
	public String getOnline() {
		return online;
	}
	
	public boolean online() {
		return "1".equals(online);
	}

	public void setOnline(String online) {
		this.online = online;
	}

    @Override
    public String toString() {
        return account;
    }
}
