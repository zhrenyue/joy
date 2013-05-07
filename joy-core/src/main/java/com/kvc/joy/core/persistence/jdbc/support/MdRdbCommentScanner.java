package com.kvc.joy.core.persistence.jdbc.support;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.lang.PackageTool;
import com.kvc.joy.commons.lang.reflect.MethodTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumnComment;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;

/**
 * <p>
 * 关系数据库注释扫描器
 * </p>
 * 
 * <pre>
 * 负责从JPA的实体中的Comment注解中获取数据库表格和字段的注释，以便用于更新到数据库表中
 * </pre>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-10 下午8:24:53
 */
public class MdRdbCommentScanner {

	private static Logger logger = LoggerFactory.getLogger(MdRdbCommentScanner.class);

	private MdRdbCommentScanner() {
	}

	/**
	 * 
	 * 
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-12 下午10:56:45
	 */
	public static List<MdRdbTable> scan() {
		List<MdRdbTable> tableList = new ArrayList<MdRdbTable>();
		Set<String> packages = PackageTool.getPackages("com.kvc.joy.**.po", true);
		for (String pkg : packages) {
			Set<Class<?>> classes = PackageTool.getClassesInPackage(pkg, false);
			for (Class<?> clazz : classes) {
				if (clazz.getName().endsWith("_") == false) {
					Entity entity = clazz.getAnnotation(Entity.class);
					if (entity != null) {
						MdRdbTable table = scanTableComment(clazz);
						List<MdRdbColumn> columns = scanColumnComment(clazz, table);
						table.setColumns(columns);
						tableList.add(table);
					}
				}
			}
		}
		return tableList;
	}

	private static MdRdbTable scanTableComment(Class<?> clazz) {
		Table tableAnno = clazz.getAnnotation(Table.class);
		MdRdbTable table = new MdRdbTable();
		table.setName(tableAnno.name());
		Comment tableCommentAnno = clazz.getAnnotation(Comment.class);
		if (tableCommentAnno != null) {
			String comment = tableCommentAnno.value();
			table.setComment(comment);
		} else {
			logger.warn("实体类" + clazz + "未配置Comment注解，将无法自动生成表注释！");
		}
		return table;
	}

	private static List<MdRdbColumn> scanColumnComment(Class<?> clazz, MdRdbTable table) {
		List<MdRdbColumn> columns = new ArrayList<MdRdbColumn>();
		List<Method> methods = MethodTool.getReadMethods(clazz);
		for (Method method : methods) {
			Transient trans = method.getAnnotation(Transient.class);
			if (trans == null) {
				Comment columnCommentAnno = method.getAnnotation(Comment.class);
				if (columnCommentAnno != null) {
					MdRdbColumn column = new MdRdbColumn();
					columns.add(column);
					Column columnAnno = method.getAnnotation(Column.class);
					String name = StringTool.upperCase(columnAnno.name());
					if (StringTool.isEmpty(name)) {
						name = StringTool.uncapitalize(method.getName().replaceFirst("^is|^get", "")).toUpperCase();
					}
					column.setName(name);
					MdRdbColumnComment comment = new MdRdbColumnComment();
					column.setComment(comment);
					comment.setBriefDesc(columnCommentAnno.value());
					comment.setDetailDesc(columnCommentAnno.detailDesc());
					comment.setCodeId(columnCommentAnno.codeId());
				}
			}
		}
		return columns;
	}

}
