/*******************************************************************************
 * Copyright (c) 2011-10-18 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.edu.dao.TutorialScheduleDao;
import com.edu.model.Tutorial;
import com.edu.model.TutorialSchedule;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-10-18
 */
@Repository("tutorialScheduleDao")
public class TutorialScheduleDaoHibernate extends
		GenericDaoHibernate<TutorialSchedule, Long> implements
		TutorialScheduleDao {
	public TutorialScheduleDaoHibernate() {
		super(TutorialSchedule.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialSchedule> getAllTutorialScheduleByTutorialId(
			Long tutorialId) {
		return getHibernateTemplate().find(
				"from TutorialSchedule t where t.tutorial.id=?", tutorialId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialSchedule> findTutorialSchedule(Date start, Date end) {
		if (start == null && end == null) {
			return new ArrayList<TutorialSchedule>();
		}
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(128);
		{
			hql.append("select distinct ts from Tutorial t ");
			hql.append(" join t.tutorialSchedules ts ");
		}
		{
			hql.append(" where t.enabled=? ");
			params.add(true);
		}
		if (start != null) {
			hql.append(" and ts.endDate>=? ");
			params.add(start);
		}
		if (end != null) {
			hql.append(" and ts.startDate<=? ");
			params.add(end);
		}
		{
			hql.append(" order by t.name");
		}
		String query = hql.toString();
		return getHibernateTemplate().find(query, params.toArray());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialSchedule> findTutorialSchedulesByUserId(
			Long tutorialId, Long userId) {
		String hql = "select distinct ts from Tutorial t join t.tutorialSchedules ts join ts.students s where t.id=? and s.id=? and t.enabled=? order by t.name";
		return getHibernateTemplate().find(hql, tutorialId, userId, true);
	}
}
