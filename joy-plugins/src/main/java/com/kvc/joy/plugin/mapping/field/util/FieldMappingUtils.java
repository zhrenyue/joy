package com.kvc.joy.plugin.mapping.field.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.bean.BeanTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.plugin.mapping.field.dao.TCfgFieldMappingRuleDao;
import com.kvc.joy.plugin.mapping.field.po.TSysFieldMapping;
import com.kvc.joy.plugin.mapping.field.po.TSysFieldMappingRule;

/**
 * 字段映射规则数据访问对象
 * @author  唐玮琳
 */
public class FieldMappingUtils {
	
	/**
	 * 对象类型：java对象 
	 */
	public static final String OBJECT_TYPE_JAVA_OBJECT = TSysFieldMappingRule.OBJECT_TYPE_JAVA_OBJECT;
	/**
	 * 对象类型：数据库表
	 */
	public static final String OBJECT_TYPE_DB_TABLE = TSysFieldMappingRule.OBJECT_TYPE_DB_TABLE;
	/**
	 * 对象类型：数据库表转为Java对象
	 */
	public static final String OBJECT_TYPE_TABLE_TO_JAVA = TSysFieldMappingRule.OBJECT_TYPE_TABLE_TO_JAVA;
	
	private Logger logger = LoggerFactory.getLogger(FieldMappingUtils.class);
	private volatile static FieldMappingUtils fieldMappingUtils;
	

	private Map<String, TSysFieldMappingRule> ruleMap;
	
	private FieldMappingUtils() {
		loadAllFieldMapping();
	}

	public static FieldMappingUtils getInstance() {
		if (fieldMappingUtils == null) {
			synchronized (FieldMappingUtils.class) {
				if (fieldMappingUtils == null) {
					fieldMappingUtils = new FieldMappingUtils();
				}
			}
		}
		return fieldMappingUtils;
	}
	
	private void loadAllFieldMapping() {
		List<TSysFieldMappingRule> ruleList = new TCfgFieldMappingRuleDao().loadAllFieldMappingRules();
		ruleMap = new HashMap<String, TSysFieldMappingRule>(ruleList.size());
		for (TSysFieldMappingRule rule : ruleList) {
			String key = makeMapKey(rule.getObject1(), rule.getObject2());
			ruleMap.put(key, rule);
		}
	}
	
	/**
	 * 根据对象名及其类型，取得对应的字段映射
	 * @param obj1 对象1的名称
	 * @param obj2 对象2的名称
	 * @param ojbType 对象类型(取本类的常量)
	 * @return Set<TCfgFieldMapping>
	 */
	public Set<TSysFieldMapping> getFieldMapping(String obj1, String obj2, String ojbType) {
		if (StringTool.isBlank(obj1) || StringTool.isBlank(obj2) || StringTool.isBlank(ojbType)) {
			throw new IllegalArgumentException("obj1, obj2, ojbType三个参数均不允许为空！");
		}
		Set<TSysFieldMapping> resultSet = null;
		if (ruleMap != null) {
			String key = makeMapKey(obj1, obj2);
			TSysFieldMappingRule rule = ruleMap.get(key);
			if (rule != null) {
				resultSet = rule.getFieldMappingSet();
			} else {
				logger.info("根据对象1的名称【" + obj1 + "】、对象2的名称【" + obj2 + "】、对象类型【" + ojbType + 
						"】，找不到对应的字段映射规则配置！");
			}
		}
		return resultSet == null ? new HashSet<TSysFieldMapping>(0) : resultSet;
	}
	
	/**
	 * 组装map的key 
	 */
	private String makeMapKey(String obj1, String obj2) {
		return obj1 + "-" + obj2;
	}
	
	/**
	 * 根据字段映射，拷贝源对象的属性，到指定目标类的对象
	 * @param destClass 目标类
	 * @param srcObj 源对象
	 * @return 目标类的对象
	 */
	public static <T> T copyProperties(Class<T> destClass, Object srcObj) {
		String srcClassName = srcObj.getClass().getSimpleName();
		String destClassName = destClass.getSimpleName();
		Set<TSysFieldMapping> fieldMappingSet = FieldMappingUtils.getInstance().getFieldMapping(srcClassName, destClassName, 
				FieldMappingUtils.OBJECT_TYPE_JAVA_OBJECT);
		Map<String, String> fieldMap = new HashMap<String, String>(fieldMappingSet.size());
		for (TSysFieldMapping fieldMapping : fieldMappingSet) {
			String field1 = fieldMapping.getField1();
			String field2 = fieldMapping.getField2();
			if (fieldMap.containsKey(field1)) {
				String tempField2 = fieldMap.get(field1);
				if (tempField2.endsWith(",") == false) {
					tempField2 += ",";
				} 
				field2 = tempField2 + field2;	
			}
			fieldMap.put(field1, field2);
		}
		return BeanTool.copyProperties(destClass, srcObj, fieldMap);
	}
	
}
