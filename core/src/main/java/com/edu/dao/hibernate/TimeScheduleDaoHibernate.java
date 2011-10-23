/*******************************************************************************
 * Copyright (c) 2011-10-18 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.edu.dao.TimeScheduleDao;
import com.edu.model.TimeSchedule;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-10-18
 */
@Repository("timeScheduleDao")
public class TimeScheduleDaoHibernate extends
		GenericDaoHibernate<TimeSchedule, Long> implements TimeScheduleDao {
	public TimeScheduleDaoHibernate() {
		super(TimeSchedule.class);
	}

	public List<TimeSchedule> getAllTimeScheduleByTutorialId(Long tutorialId) {
		return getHibernateTemplate().find(
				"from TimeSchedule t where t.tutorial.id=?", tutorialId);
	}
}
