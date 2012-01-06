/*******************************************************************************
 * Copyright (c) 2011-11-20 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.service;

import com.edu.model.TutorialScheduleStudentKey;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-11-20
 */
public interface DuetimeNotificationManager {

	/**
	 * get notification minute before a tutorial class or workshop start
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2012-1-2
	 */
	int getNotificationMinute();

	/**
	 * notification a tutorial class or workshop
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2012-1-2
	 */
	void notification();

	/**
	 * notification a single tutorialSchedule
	 * @param id
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2012-1-2
	 */
	void notification(TutorialScheduleStudentKey id);
}
