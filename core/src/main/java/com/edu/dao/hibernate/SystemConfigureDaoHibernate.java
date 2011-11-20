/*******************************************************************************
 * Copyright (c) 2011-11-20 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.edu.dao.SystemConfigureDao;
import com.edu.model.SystemConfigure;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-11-20
 */
@Repository("systemConfigureDao")
public class SystemConfigureDaoHibernate extends
		GenericDaoHibernate<SystemConfigure, Long> implements
		SystemConfigureDao {

	public SystemConfigureDaoHibernate() {
		super(SystemConfigure.class);
	}

	public void init() {
		try {
			if (!exists(SystemConfigure.NOTIFICATION_DUETIME)) {
				super.save(new SystemConfigure(null,
						SystemConfigure.NOTIFICATION_DUETIME, "15"));
			}
		} catch (Exception e) {
		}
	}

	public boolean exists(String name) {
		return get(name) != null;
	}

	public SystemConfigure get(String name) {
		String hql = "from SystemConfigure where name=?";
		List<SystemConfigure> find = getHibernateTemplate().find(hql, name);
		return find.isEmpty() ? null : find.get(0);
	}
}
