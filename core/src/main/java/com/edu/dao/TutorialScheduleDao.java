/*******************************************************************************
 * Copyright (c) 2011-10-18 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.edu.model.TutorialSchedule;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-10-18
 */
public interface TutorialScheduleDao extends GenericDao<TutorialSchedule, Long> {

	/**
	 * get all tutorialSchedules by the tutorial id
	 * @param tutorialId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	List<TutorialSchedule> getAllTutorialScheduleByTutorialId(Long tutorialId);

	/**
	 * find tutor's TutorialSchedule by date between start and end and tutorId
	 * @param start
	 * @param end
	 * @param userId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2012-1-18
	 */
	List<TutorialSchedule> findTutorTutorialSchedule(Date start, Date end,
			Long userId);

	/**
	 * find student's TutorialSchedule by date between start and end and tutorId
	 * @param start
	 * @param end
	 * @param userId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2012-1-18
	 */
	List<TutorialSchedule> findStudentTutorialSchedule(Date start, Date end,
			Long userId);

	/**
	 * find tutor's and student's TutorialSchedule by date between start and end and tutorId
	 * @param start
	 * @param end
	 * @param userId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2012-1-18
	 */
	List<TutorialSchedule> findTutorAndStudentTutorialSchedule(Date start,
			Date end, Long userId);
}
