/*******************************************************************************
 * Copyright (c) 2011-11-18 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.edu.model.TutorialScheduleStudent;
import com.edu.model.TutorialScheduleStudentKey;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-11-18
 */
public interface TutorialScheduleStudentDao extends
		GenericDao<TutorialScheduleStudent, TutorialScheduleStudentKey> {

	/**
	 * remove a student's tutorial schedule
	 * @param tutorialScheduleId
	 * @param studentId
	 * @param fromTime
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-18
	 */
	void remove(Long tutorialScheduleId, Long studentId, Date fromTime);

	/**
	 * remove a student's tutorial schedules
	 * @param tutorialId
	 * @param studentId
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-18
	 */
	void remove(Long tutorialId, Long studentId);

	/**
	 * find student's tutorial schedules by tutorialId and userId
	 * @param tutorialId
	 * @param userId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-19
	 */
	List<TutorialScheduleStudent> findTutorialSchedulesByUserId(
			Long tutorialId, Long userId);

	/**
	 * find student's tutorial schedule between start and end date.
	 * @param studentId
	 * @param start
	 * @param end
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-27
	 */
	List<TutorialScheduleStudent> findTutorialSchedulesByStudentIdAndDate(
			Long studentId, Date start, Date end);

	/**
	 * find student's tutorial schedules between start date and end date
	 * @param userId
	 * @param start
	 * @param end
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-20
	 */
	List<TutorialScheduleStudent> findTutorialSchedules(Long userId,
			Date start, Date end);

	/**
	 * find tutorial schedules those need notification
	 * @param beforeMinutes
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-20
	 */
	List<TutorialScheduleStudent> findNeedToNotification(int beforeMinutes);

	/**
	 * Whether can have more participate
	 * @param tutorialScheduleId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-12-31
	 */
	boolean canHasMoreParticipate(Long tutorialScheduleId);

	/**
	 * get participate count
	 * @param tutorialScheduleId
	 * @param lectureDate
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-12-31
	 */
	int countParticipate(Long tutorialScheduleId, Date lectureDate);

	/**
	 * find someone's tutorial schedules those need to notification
	 * @param userId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2012-1-2
	 */
	List<TutorialScheduleStudent> findNeedToNotificationByUserId(Long userId);
}
