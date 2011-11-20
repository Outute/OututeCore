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

	public void notification();

	public void notification(TutorialScheduleStudentKey id);
}
