/*******************************************************************************
 * Copyright (c) 2011-11-20 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.dao.SystemConfigureDao;
import com.edu.dao.TutorialScheduleStudentDao;
import com.edu.model.SystemConfigure;
import com.edu.model.TutorialScheduleStudent;
import com.edu.model.TutorialScheduleStudentKey;
import com.edu.model.User;
import com.edu.service.DuetimeNotificationManager;
import com.edu.service.MailEngine;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-11-20
 */
public class DuetimeNotificationManagerImpl implements
		DuetimeNotificationManager {
	private SystemConfigureDao systemConfigureDao;
	private TutorialScheduleStudentDao tutorialScheduleStudentDao;

	private MailEngine mailEngine;

	private String templateName;

	private String sendFrom;

	private String subject;

	public void setSystemConfigureDao(SystemConfigureDao systemConfigureDao) {
		this.systemConfigureDao = systemConfigureDao;
	}

	public void setTutorialScheduleStudentDao(
			TutorialScheduleStudentDao tutorialScheduleStudentDao) {
		this.tutorialScheduleStudentDao = tutorialScheduleStudentDao;
	}

	public void setMailEngine(MailEngine mailEngine) {
		this.mailEngine = mailEngine;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getNotificationMinute() {
		SystemConfigure cfg = systemConfigureDao
				.get(SystemConfigure.NOTIFICATION_DUETIME);
		Integer minute = cfg == null ? SystemConfigure.NOTIFICATION_MINUTE_DEFAULT
				: cfg.getIntegerValue();
		minute = minute == null ? SystemConfigure.NOTIFICATION_MINUTE_DEFAULT
				: minute;
		return minute;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void notification() {
		Integer minute = getNotificationMinute();
		List<TutorialScheduleStudent> list = tutorialScheduleStudentDao
				.findNeedToNotification(minute);
		for (TutorialScheduleStudent tss : list) {
			notification(tss);
		}
	}

	/**
	 * {@inheritDoc}
	 */
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
			User student = tss.getStudent();
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(sendFrom);
			mailMessage.setSubject(subject);
			mailMessage.setTo(student.getFullName() + "<" + student.getEmail()
					+ ">");

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", student);
			// TODO: figure out how to get bundle specified in struts.xml
			// model.put("bundle", getTexts());
			model.put("message", tss.toString());
			model.put("applicationURL", "http://www.outute.com");
			mailEngine.sendMessage(mailMessage, templateName, model);
			System.out.println("Notification:" + tss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateFlag(tss);
	}

}
