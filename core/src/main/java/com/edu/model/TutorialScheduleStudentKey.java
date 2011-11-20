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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-11-18
 */
@Embeddable
public class TutorialScheduleStudentKey implements Serializable {

	private static final long serialVersionUID = 5762684522933463741L;

	@Column(name = "tutorial_schedule_id")
	private Long tutorialScheduleId;
	@Column(name = "user_id")
	private Long studentId;
	@Temporal(TemporalType.DATE)
	@Column(name = "lecture_date")
	private Date lectureDate;

	public TutorialScheduleStudentKey() {
	}

	public TutorialScheduleStudentKey(Long tutorialScheduleId, Long studentId,
			Date lectureDate) {
		this.tutorialScheduleId = tutorialScheduleId;
		this.studentId = studentId;
		this.lectureDate = lectureDate;
	}

	public Long getTutorialScheduleId() {
		return tutorialScheduleId;
	}

	public void setTutorialScheduleId(Long tutorialScheduleId) {
		this.tutorialScheduleId = tutorialScheduleId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Date getLectureDate() {
		return lectureDate;
	}

	public void setLectureDate(Date lectureDate) {
		this.lectureDate = lectureDate;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lectureDate == null) ? 0 : lectureDate.hashCode());
		result = prime * result
				+ ((studentId == null) ? 0 : studentId.hashCode());
		result = prime
				* result
				+ ((tutorialScheduleId == null) ? 0 : tutorialScheduleId
						.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TutorialScheduleStudentKey other = (TutorialScheduleStudentKey) obj;
		if (lectureDate == null) {
			if (other.lectureDate != null)
				return false;
		} else if (!lectureDate.equals(other.lectureDate))
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		if (tutorialScheduleId == null) {
			if (other.tutorialScheduleId != null)
				return false;
		} else if (!tutorialScheduleId.equals(other.tutorialScheduleId))
			return false;
		return true;
	}

	public String toString() {
		return "TutorialScheduleStudentKey [fromTime=" + lectureDate
				+ ", studentId=" + studentId + ", tutorialScheduleId="
				+ tutorialScheduleId + "]";
	}

}
