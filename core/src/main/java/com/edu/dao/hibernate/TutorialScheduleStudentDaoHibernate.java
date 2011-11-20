/*******************************************************************************
 * Copyright (c) 2011-11-18 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.dao.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.edu.dao.TutorialScheduleStudentDao;
import com.edu.model.TutorialScheduleStudent;
import com.edu.model.TutorialScheduleStudentKey;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-11-18
 */
@Repository("tutorialScheduleStudentDao")
public class TutorialScheduleStudentDaoHibernate
		extends
		GenericDaoHibernate<TutorialScheduleStudent, TutorialScheduleStudentKey>
		implements TutorialScheduleStudentDao {

	public TutorialScheduleStudentDaoHibernate() {
		super(TutorialScheduleStudent.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(Long tutorialScheduleId, Long studentId, Date fromTime) {
		TutorialScheduleStudentKey id = new TutorialScheduleStudentKey(
				tutorialScheduleId, studentId, fromTime);
		if (exists(id)) {
			super.remove(id);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(Long tutorialId, Long studentId) {
		String hql = "from TutorialScheduleStudent where tutorialSchedule.tutorial.id=? and id.studentId=?";
		List<TutorialScheduleStudent> find = getHibernateTemplate().find(hql,
				tutorialId, studentId);
		for (TutorialScheduleStudent tss : find) {
			remove(tss.getId());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialScheduleStudent> findTutorialSchedulesByUserId(
			Long tutorialId, Long userId) {
		String hql = "select distinct tss from TutorialScheduleStudent tss join tss.tutorialSchedule.tutorial t where t.id=? and tss.student.id=? and t.enabled=? order by t.name";
		return getHibernateTemplate().find(hql, tutorialId, userId, true);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialScheduleStudent> findTutorialSchedules(Long userId,
			Date start, Date end) {
		String hql = "select distinct tss from TutorialScheduleStudent tss where tss.student.id=? and tss.lectureTime between ? and ? order by tss.lectureTime";
		return getHibernateTemplate().find(hql, userId, start, end);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialScheduleStudent> findNeedToNotification(
			int beforeMinutes) {
		Date start = new Date();
		Date end = start;
		{
			Calendar c = Calendar.getInstance();
			c.setTime(end);
			c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + beforeMinutes);
			end = c.getTime();
		}
		{
			String hql = "select max(tss.lastUpdateTime) from TutorialScheduleStudent tss";
			List<Date> find = getHibernateTemplate().find(hql);
			start = find.isEmpty() || find.get(0) == null ? start : find.get(0);
		}
		String hql = "select distinct tss from TutorialScheduleStudent tss where tss.flag=? and tss.lectureTime between ? and ?";
		return getHibernateTemplate().find(hql,
				TutorialScheduleStudent.FLAG_DEFAULT, start, end);
	}
}
