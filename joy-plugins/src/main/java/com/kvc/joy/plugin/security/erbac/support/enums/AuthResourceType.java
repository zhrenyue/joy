package com.kvc.joy.plugin.security.erbac.support.enums;

import com.kvc.joy.commons.enums.EnumTool;
import com.kvc.joy.commons.enums.ICodeEnum;

public enum AuthResourceType implements ICodeEnum {

	URL("01", "URL"),
	METHOD("02", "类的方法");
	
	private String code;
	private String desc;
	
	AuthResourceType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}

	public String getTrans() {
		return desc;
	}
	
	public static AuthResourceType enumOf(String code) {
		return EnumTool.enumOf(AuthResourceType.class, code);
	}

}