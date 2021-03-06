package org.joy.core.persistence.support.entity;

import org.joy.commons.bean.IEntity;
import org.joy.commons.lang.string.StringTool;
import org.joy.commons.support.IdGenerator;
import org.joy.core.persistence.orm.jpa.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 主键生成策略是UUID的实体基类
 * 
 * @author Kevice
 * @time 2012-6-17 下午9:45:42
 * @since 1.0.0
 */
@MappedSuperclass
public class UuidEntity implements IEntity<String> {

	protected String id;

	public UuidEntity() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UuidEntity other = (UuidEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Id
	@Column(length = 32)
	@Comment(value="主键", detailDesc="生成策略: UUID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public static void setUuid(Object entity) {
		if (entity instanceof UuidEntity) {
			UuidEntity uuidEntity = (UuidEntity)entity;
			String id = uuidEntity.getId();
			if (StringTool.isBlank(id)) {
				uuidEntity.setId(IdGenerator.gen32Uuid());
			}
		}
	}

}
