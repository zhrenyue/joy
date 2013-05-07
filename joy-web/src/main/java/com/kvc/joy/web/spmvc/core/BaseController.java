package com.kvc.joy.web.spmvc.core;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.commons.lang.GenericTool;
import com.kvc.joy.core.persistence.orm.jpa.JpaUtils;
import com.kvc.joy.core.rp.pagestore.PageStore;
import com.kvc.joy.core.rp.pagestore.PageStoreCreator;
import com.kvc.joy.web.support.utils.HttpRequestUtils;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月7日 上午8:50:53
 */
public abstract class BaseController<T extends IEntity<?>> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected abstract String getCurrentViewName();

	protected String getDetailViewName() {
		return getCurrentViewName() + "Detail";
	}

	protected String getEditViewName() {
		return getCurrentViewName() + "Edit";
	}

	@RequestMapping("")
	public String page(Model model) {
		return getCurrentViewName();
	}

	@RequestMapping("/list")
	public String list(Model model) {
		PageStore pageStore = getPageStore();
		pageStore.query(getEntityClass());
		pageStore.getPaging().cal();
		model.addAttribute("pageStore", pageStore);
		addAttributes(model);
		return getCurrentViewName();
	}

	@RequestMapping("/get")
	public String get(Model model) {
		String id = HttpRequestUtils.getParameter("id");
		T m = JpaUtils.get(getEntityClass(), convertId(id));
		model.addAttribute("model", m);
		return getDetailViewName();
	}

	protected Object convertId(String id) {
		return id;
	}

	protected void addAttributes(Model model) {
		Map<String, String[]> parameterMap = HttpRequestUtils.getParameterMap();
		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			String[] values = entry.getValue();
			Object value = null;
			if (values.length == 0) {
				value = "";
			} else if (values.length == 1) {
				value = values[0];
			} else {
				value = values;
			}
			model.addAttribute(entry.getKey(), value);
		}
	}

	protected PageStore getPageStore() {
		Map<String, String> paramMap = HttpRequestUtils.getParameters();
		PageStore store = new PageStoreCreator(paramMap).create();
		Order[] defaultOrders = getDefaultOrders();
		if (defaultOrders != null) {
			Map<String, String> orderMap = store.getQueryLogics().getOrderMap();
			for (Order order : defaultOrders) {
				if (orderMap.containsKey(order.getProperty()) == false) {
					orderMap.put(order.getProperty(), order.getDirection().name());
				}
			}
		}
		return store;
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		Class<?> clazz = GenericTool.getSuperClassGenricType(getClass());
		if (clazz.equals(Object.class)) {
			throw new SystemException("继承BaseController的子类必须指定泛型参数！");
		} else if (IEntity.class.isAssignableFrom(clazz) == false) {
			throw new SystemException("继承BaseController的子类的泛型参数类型必须为实现IEntity接口的实体类！");
		}
		return (Class<T>) clazz;
	}

	protected Order[] getDefaultOrders() {
		return null;
	}

}
