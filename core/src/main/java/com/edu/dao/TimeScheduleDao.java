/*******************************************************************************
 * Copyright (c) 2011-10-18 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.edu.model.TimeSchedule;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-10-18
 */
public interface TimeScheduleDao extends GenericDao<TimeSchedule, Long> {

	/**
	 * get all timeschedules by the tutorial id
	 * @param tutorialId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	public List<TimeSchedule> getAllTimeScheduleByTutorialId(Long tutorialId);
}
