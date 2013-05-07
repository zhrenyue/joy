package com.kvc.joy.core.persistence.orm.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.core.rp.pagestore.PageStore;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-26 下午8:57:12
 */
public class JpaEntityRepository<T extends IEntity<ID>, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements IJpaEntityRepository<T, ID> {

	private Class<T> entityClass;

	public JpaEntityRepository(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityClass = domainClass;
	}

	public List<T> searchAll(Order... orders) {
		return JpaUtils.searchAll(entityClass, orders);
	}

	public List<T> inSearch(String attr, Collection<?> values, Order... orders) {
		return JpaUtils.inSearch(entityClass, attr, values, orders);
	}

	public List<T> inSearch(Collection<?> values, Order... orders) {
		return JpaUtils.inSearch(entityClass, values, orders);
	}

	public List<T> search(String attr, Object value, Order... orders) {
		return JpaUtils.search(entityClass, attr, value, orders);
	}

	public List<T> andSearch(Map<String, Object> attrMap, Order... orders) {
		return JpaUtils.andSearch(entityClass, attrMap, orders);
	}

	public List<T> orSearch(Map<String, Object> attrMap, Order... orders) {
		return JpaUtils.orSearch(entityClass, attrMap, orders);
	}

	public List<T> pagingSearch(PageStore pageStore) {
		return JpaUtils.pagingSearch(entityClass, pageStore);
	}

	public <E> List<T> inSearch(SingularAttribute<? super T, E> attr, Collection<E> values, Order... orders) {
		return JpaUtils.inSearch(entityClass, attr, values, orders);
	}

	public <F> List<T> search(SingularAttribute<? super T, F> attr, F value, Order... orders) {
		return JpaUtils.search(entityClass, attr, value, orders);
	}

	public <E> List<T> andQuery(Map<SingularAttribute<? super T, E>, E> attrMap, Order... orders) {
		return JpaUtils.andQuery(entityClass, attrMap, orders);
	}

	public <E> List<T> orQuery(Map<SingularAttribute<? super T, E>, E> attrMap, Order... orders) {
		return JpaUtils.orQuery(entityClass, attrMap, orders);
	}

	public List<?> findBySql(String sql) {
		return JpaUtils.findBySql(sql);
	}

	public long querySequence(String sequence) {
		return JpaUtils.querySequence(sequence);
	}

}
