package com.edu.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;

/**
 * This class represents the basic "tutorial" object in WeTute that allows for tutorial management.
 *
 * @author Reid
 */
@Entity
@Table(name = "time_schedule")
@Searchable
@javax.xml.bind.annotation.XmlRootElement
public class TimeSchedule extends BaseObject implements Serializable {

	private static final long serialVersionUID = -2146302697966361308L;
	public static final int REPEAT_NO = 0;
	public static final int REPEAT_WEEKLY = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SearchableId
	private Long id;
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FROM_TIME", nullable = false)
	private Date fromTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TO_TIME", nullable = false)
	private Date toTime;
	@Column(name = "REPEAT_TYPE", nullable = false)
	private int repeat;
	@Column(name = "ENDS_OCCURRENCE", nullable = false)
	private int endsOccurrence;
	@Column(name = "COST")
	private int cost;
	@Column(name = "MAX_PARTICIPATE")
	private int maxParticipate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME")
	private Date modifyTime;

	@ManyToOne
	@JoinColumn(name = "TUTORIAL_ID", nullable = false)
	private Tutorial tutorial;
	@Version
	private Integer version;

	/**
	 * Default constructor - creates a new instance with no values set.
	 */
	public TimeSchedule() {
	}

	// getter and setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	public int getEndsOccurrence() {
		return endsOccurrence;
	}

	public void setEndsOccurrence(int endsOccurrence) {
		this.endsOccurrence = endsOccurrence;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getMaxParticipate() {
		return maxParticipate;
	}

	public void setMaxParticipate(int maxParticipate) {
		this.maxParticipate = maxParticipate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Tutorial getTutorial() {
		return tutorial;
	}

	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
	}

	public Integer getVersion() {
		return version;
	}

	protected void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		HashCodeBuilder hc = new HashCodeBuilder();
		hc.append(id).append(cost).append(createTime).append(endsOccurrence)
				.append(fromTime).append(maxParticipate).append(modifyTime)
				.append(repeat).append(startDate).append(toTime).append(
						tutorial);
		return hc.toHashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TimeSchedule other = (TimeSchedule) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(this.id, other.id).append(this.cost, other.cost).append(
				this.createTime, other.createTime).append(this.endsOccurrence,
				other.endsOccurrence).append(this.fromTime, other.fromTime)
				.append(this.maxParticipate, other.maxParticipate).append(
						this.modifyTime, other.modifyTime).append(this.repeat,
						other.repeat).append(this.startDate, other.startDate)
				.append(this.toTime, other.toTime).append(this.tutorial,
						other.tutorial);
		return eb.isEquals();
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("id", id).append("cost",
				cost).append("createTime", createTime).append("endsOccurrence",
				endsOccurrence).append("fromTime", fromTime).append(
				"maxParticipate", maxParticipate).append("modifyTime",
				modifyTime).append("repeat", repeat).append("startDate",
				startDate).append("toTime", toTime)
				.append("tutorial", tutorial);
		return sb.toString();
	}
}
