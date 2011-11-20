/*******************************************************************************
 * Copyright (c) 2011-11-18 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.appfuse.model.BaseObject;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-11-18
 */
/**
 * @author Tyler
 *
 */
@Entity
@Table(name = "tutorial_schedule_student")
@javax.xml.bind.annotation.XmlRootElement
public class TutorialScheduleStudent extends BaseObject implements Serializable {

	private static final long serialVersionUID = 5762684522933463741L;

	public static final int FLAG_DEFAULT = 0;
	public static final int FLAG_PROCESSING = 1;
	public static final int FLAG_DONE = 2;

	@EmbeddedId
	private TutorialScheduleStudentKey id;

	@Column(name = "flag", nullable = false)
	private int flag = FLAG_DEFAULT;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update_time", nullable = true)
	private Date lastUpdateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lecture_time", nullable = false)
	private Date lectureTime;

	@ManyToOne
	@JoinColumn(name = "tutorial_schedule_id", insertable = false, updatable = false, nullable = true)
	private TutorialSchedule tutorialSchedule;
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = true)
	private User student;

	public TutorialScheduleStudent() {
	}

	public TutorialScheduleStudent(TutorialScheduleStudentKey id,
			Date lectureTime) {
		this.id = id;
		this.lectureTime = lectureTime;
	}

	public TutorialScheduleStudent(Long tutorialScheduleId, Long studentId,
			Date fromTime, Date lectureTime) {
		this(new TutorialScheduleStudentKey(tutorialScheduleId, studentId,
				fromTime), lectureTime);
	}

	public TutorialScheduleStudentKey getId() {
		return id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLectureTime() {
		return lectureTime;
	}

	public void setLectureTime(Date lectureTime) {
		this.lectureTime = lectureTime;
	}

	public void setId(TutorialScheduleStudentKey id) {
		this.id = id;
	}

	public TutorialSchedule getTutorialSchedule() {
		return tutorialSchedule;
	}

	public User getStudent() {
		return student;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TutorialScheduleStudent other = (TutorialScheduleStudent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return "TutorialScheduleStudent [flag=" + flag + ", id=" + id
				+ ", lastUpdateTime=" + lastUpdateTime + ", lectureTime="
				+ lectureTime + "]";
	}

}
