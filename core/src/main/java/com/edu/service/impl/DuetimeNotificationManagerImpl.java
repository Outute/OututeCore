/*******************************************************************************
 * Copyright (c) 2011-11-20 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.dao.SystemConfigureDao;
import com.edu.dao.TutorialScheduleStudentDao;
import com.edu.model.SystemConfigure;
import com.edu.model.TutorialScheduleStudent;
import com.edu.model.TutorialScheduleStudentKey;
import com.edu.service.DuetimeNotificationManager;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-11-20
 */
@Service("duetimeNotificationManager")
public class DuetimeNotificationManagerImpl implements
		DuetimeNotificationManager {
	@Autowired
	private SystemConfigureDao systemConfigureDao;
	@Autowired
	private TutorialScheduleStudentDao tutorialScheduleStudentDao;

	public void setSystemConfigureDao(SystemConfigureDao systemConfigureDao) {
		this.systemConfigureDao = systemConfigureDao;
	}

	public void setTutorialScheduleStudentDao(
			TutorialScheduleStudentDao tutorialScheduleStudentDao) {
		this.tutorialScheduleStudentDao = tutorialScheduleStudentDao;
	}

	public void notification() {
		SystemConfigure cfg = systemConfigureDao
				.get(SystemConfigure.NOTIFICATION_DUETIME);
		Integer minute = cfg == null ? 15 : cfg.getIntegerValue();
		minute = minute == null ? 15 : minute;
		List<TutorialScheduleStudent> list = tutorialScheduleStudentDao
				.findNeedToNotification(minute);
		for (TutorialScheduleStudent tss : list) {
			notification(tss);
		}
	}

	public void notification(TutorialScheduleStudentKey id) {
		TutorialScheduleStudent tss = tutorialScheduleStudentDao.get(id);
		if (tss != null) {
			notification(tss);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	protected void updateFlag(TutorialScheduleStudent tss) {
		if (tss.getFlag() == TutorialScheduleStudent.FLAG_DEFAULT) {
			tss.setFlag(TutorialScheduleStudent.FLAG_PROCESSING);
		} else if (tss.getFlag() == TutorialScheduleStudent.FLAG_PROCESSING) {
			tss.setFlag(TutorialScheduleStudent.FLAG_DONE);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	protected void notification(TutorialScheduleStudent tss) {
		updateFlag(tss);
		try {
			tss.setFlag(TutorialScheduleStudent.FLAG_PROCESSING);
			//TODO
			System.out.println("Notification:" + tss);
		} catch (Exception e) {
		}
		updateFlag(tss);
	}

}
