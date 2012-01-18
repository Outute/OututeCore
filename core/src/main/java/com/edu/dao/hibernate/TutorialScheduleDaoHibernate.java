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
import com.edu.model.TutorialSchedule;
import com.edu.util.DateUtil;

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
	public List<TutorialSchedule> findTutorTutorialSchedule(Date start,
			Date end, Long userId) {
		return findTutorialSchedule(start, end, userId, true);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialSchedule> findStudentTutorialSchedule(Date start,
			Date end, Long userId) {
		return findTutorialSchedule(start, end, userId, false);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialSchedule> findTutorAndStudentTutorialSchedule(
			Date start, Date end, Long userId) {
		return findTutorialSchedule(start, end, userId, null);
	}

	private List<TutorialSchedule> findTutorialSchedule(Date start, Date end,
			Long userId, Boolean nullAll_trueTutor_falseStudent) {
		if (start == null && end == null && userId == null) {
			return new ArrayList<TutorialSchedule>();
		}
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(128);
		{
			hql.append("select distinct ts from Tutorial t ");
			hql.append(" join t.tutorialSchedules ts ");
		}
		if (userId != null) {
			if (nullAll_trueTutor_falseStudent == null) {
				hql.append(" join t.tutors tutor ");
				hql.append(" left join ts.tutorialScheduleStudents tss ");
			} else if (nullAll_trueTutor_falseStudent) {
				hql.append(" join t.tutors tutor ");
			} else {
				hql.append(" join ts.tutorialScheduleStudents tss ");
			}
		}
		{
			hql.append(" where t.enabled=? and ts.endDate>=? ");
			params.add(true);
			Date d = DateUtil.clearTimes(new Date()).getTime();
			if (start == null || d.after(start)) {
				params.add(d);
			} else {
				params.add(start);
			}
		}
		if (userId != null) {
			if (nullAll_trueTutor_falseStudent == null) {
				hql.append(" and (tutor.id=? or tss.student.id=?) ");
				params.add(userId);
				params.add(userId);
			} else if (nullAll_trueTutor_falseStudent) {
				hql.append(" and tutor.id=? ");
				params.add(userId);
			} else {
				hql.append(" and tss.student.id=? ");
				params.add(userId);
			}
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

}
