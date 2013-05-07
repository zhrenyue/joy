package com.kvc.joy.plugin.security.erbac.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.kvc.joy.core.persistence.orm.jpa.BaseJpaDao;
import com.kvc.joy.core.persistence.orm.jpa.JpaPagingSupport;
import com.kvc.joy.core.rp.pagestore.PageStoreFactory;
import com.kvc.joy.plugin.security.erbac.dao.IErbacUserRepository;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup_;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacUser;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacUser_;


/**
 * 
 * @author 唐玮琳
 * @time 2012-6-28 下午7:55:31
 */
public class ErbacUserRepository extends BaseJpaDao<TErbacUser> implements IErbacUserRepository {

	public Map<TErbacGroup, Collection<TErbacUser>> getUsersByGroupIds(Collection<String> groudIds) {
		Map<TErbacGroup, Collection<TErbacUser>> resultMap = new HashMap<TErbacGroup, Collection<TErbacUser>>();
		CriteriaQuery<Tuple> q = getCriteriaBuilder().createTupleQuery();
		Root<TErbacUser> c = q.from(TErbacUser.class);
		Join<TErbacUser, Collection<TErbacGroup>> o = c.join(TErbacUser_.groups, JoinType.LEFT);
		q.select(getCriteriaBuilder().tuple(c, o));
		q.where(o.get(TErbacGroup_.id.getName()).in(groudIds));
//		q.orderBy(convertOrder(c, PageStoreFactory.curQueryLogics().getOrderArray()));
//		List<Tuple> resultList = JpaPagingSupport.paging(em, q, c);
		List<Tuple> resultList = null; //TODO
		for (Tuple tuple : resultList) {
			TErbacUser user = tuple.get(c);
			TErbacGroup group = (TErbacGroup) tuple.get(1);
			List<TErbacUser> userList = (List<TErbacUser>) resultMap.get(group);
			if (userList == null) {
				userList = new ArrayList<TErbacUser>();
				resultMap.put(group, userList);
			}
			userList.add(user);
		}
		return resultMap;
	}

}
