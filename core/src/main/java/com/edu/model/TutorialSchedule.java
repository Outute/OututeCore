package com.edu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

import com.edu.util.DateUtil;

/**
 * This class represents the basic "tutorial" object in WeTute that allows for tutorial management.
 *
 * @author Reid
 */
@Entity
@Table(name = "tutorial_schedule")
@Searchable
@javax.xml.bind.annotation.XmlRootElement
public class TutorialSchedule extends BaseObject implements Serializable {

	private static final long serialVersionUID = -2146302697966361308L;
	public static final int DURATION_NO_REPEAT = DateUtil.DURATION_NO_REPEAT;
	public static final int DURATION_DAYLY = DateUtil.DURATION_DAYLY;
	public static final int DURATION_WEEKLY = DateUtil.DURATION_WEEKLY;
	public static final int DURATION_BI_WEEKLY = DateUtil.DURATION_BI_WEEKLY;
	public static final int DURATION_MONTHLY = DateUtil.DURATION_MONTHLY;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SearchableId
	private Long id;
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", nullable = false)
	private Date endDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "from_time", nullable = false)
	private Date fromTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "to_time", nullable = false)
	private Date toTime;
	@Column(name = "duration_type", nullable = false)
	private int durationType;
	@Column(name = "ends_occurrence", nullable = false)
	private int endsOccurrence;
	@Column(name = "cost")
	private int cost;
	@Column(name = "max_participate")
	private int maxParticipate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	private Date modifyTime;
	@ManyToOne
	@JoinColumn(name = "tutorial_id", nullable = false)
	private Tutorial tutorial;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "tutorial_schedule_student", joinColumns = { @JoinColumn(name = "tutorial_schedule_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private Set<User> students; // required
	@Version
	private Integer version;

	/**
	 * Default constructor - creates a new instance with no values set.
	 */
	public TutorialSchedule() {
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public int getDurationType() {
		return durationType;
	}

	public void setDurationType(int durationType) {
		this.durationType = durationType;
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

	public Set<User> getStudents() {
		return students;
	}

	public void setStudents(Set<User> students) {
		this.students = students;
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
				.append(durationType).append(startDate).append(endDate).append(
						toTime).append(tutorial);
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
		TutorialSchedule other = (TutorialSchedule) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(this.id, other.id).append(this.cost, other.cost).append(
				this.createTime, other.createTime).append(this.endsOccurrence,
				other.endsOccurrence).append(this.fromTime, other.fromTime)
				.append(this.maxParticipate, other.maxParticipate).append(
						this.modifyTime, other.modifyTime).append(
						this.durationType, other.durationType).append(
						this.startDate, other.startDate).append(this.endDate,
						other.endDate).append(this.toTime, other.toTime)
				.append(this.tutorial, other.tutorial);
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
				modifyTime).append("durationType", durationType).append(
				"startDate", startDate).append("endDate", endDate).append(
				"toTime", toTime).append("tutorial", tutorial);
		return sb.toString();
	}
}
