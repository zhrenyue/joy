package com.kvc.joy.core.persistence.flyway.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.core.persistence.orm.jpa.annotations.DefaultValue;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月15日 上午11:12:58
 */
@IdClass(TSysDbSchemaVersionPk.class)
@Entity
@Table(name = "t_sys_db_schema_version")
@Comment("数据库脚本脚本")
public class TSysDbSchemaVersion implements IEntity<TSysDbSchemaVersionPk>{

	private int versionRank;
	private int installedRank;
	private String versionDomain; // 版本域 (扩展flyway的字段)
	private String version;
	private String desc;
	private String type;
	private String script;
	private int checksum;
	private String installedBy;
	private String installedOn;
	private int executionTime;
	private boolean success;

	@Id
	@Column(length=32)
	@Comment("版本域")
	public String getVersionDomain() {
		return versionDomain;
	}

	public void setVersionDomain(String versionDomain) {
		this.versionDomain = versionDomain;
	}
	
	@Id
	@Comment("版本序号")
	public int getVersionRank() {
		return versionRank;
	}

	public void setVersionRank(int versionRank) {
		this.versionRank = versionRank;
	}

	@Column(nullable = false)
	@Comment("安装序号")
	public int getInstalledRank() {
		return installedRank;
	}

	public void setInstalledRank(int installedRank) {
		this.installedRank = installedRank;
	}

	@Column(length = 64, nullable = false)
	@Comment("版本")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "DESCRIPTION", nullable = false)
	@Comment("描述")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(length = 20, nullable = false)
	@Comment("类型")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(length = 128, nullable = false)
	@Comment("脚本")
	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	@Column(nullable = true)
	@Comment("校验和")
	public int getChecksum() {
		return checksum;
	}

	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}

	@Column(length = 100)
	@Comment("安装用户")
	public String getInstalledBy() {
		return installedBy;
	}

	public void setInstalledBy(String installedBy) {
		this.installedBy = installedBy;
	}

	@Column(length=17, nullable = true)
	@Comment("安装时间")
	@DefaultValue("222222")
	public String getInstalledOn() {
		return installedOn;
	}

	public void setInstalledOn(String installedOn) {
		this.installedOn = installedOn;
	}

	@Column(nullable = false)
	@Comment("耗时")
	public int getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	@Column(nullable = false)
	@Comment("是否成功")
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public TSysDbSchemaVersionPk getId() {
		return new TSysDbSchemaVersionPk(versionRank, versionDomain);
	}

	@Override
	public void setId(TSysDbSchemaVersionPk id) {
		this.versionRank = id.getVersionRank();
		this.versionDomain = id.getVersionDomain();
	}

}
