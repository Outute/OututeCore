package com.edu.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * This class represents the basic "tutorial" object in WeTute that allows for tutorial management.
 *
 * @author Reid
 */
@Entity
@Table(name = "tutorial")
@Searchable
@XmlRootElement
public class Tutorial extends BaseObject implements Serializable {
	private static final long serialVersionUID = 6070037639785281128L;
	public static final int TYPE_CLASS=0;
	public static final int TYPE_WORKSHOP=1;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SearchableId
	private Long id;
	@Column(name = "name", nullable = false, length = 50, unique = true)
	@SearchableProperty
	private String name; // required
	@Column(name = "description", length = 256)
	@SearchableProperty
	private String description;
	@Column(name = "type", nullable = false)
	private int type; // required
	@Column(name = "category", nullable = false)
	private int category; // required
	/** when is Workshop type set cost for all schedule **/
	@Column(name = "cost", nullable = false)
	private int cost; // required
	/** when is Workshop type set maxParticipate for all schedule **/
	@Column(name = "maxParticipate", nullable = false)
	private int maxParticipate;
	@Column(name = "method", nullable = false)
	private int method; // required
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "tutorial_tutor", joinColumns = { @JoinColumn(name = "tutorial_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private Set<User> tutors; // required
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "tutorial_student", joinColumns = { @JoinColumn(name = "tutorial_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private Set<User> students; // required
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "tutorial")
	private Set<TutorialSchedule> tutorialSchedules;
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	@Column(name = "tutorialExpired", nullable = true)
	private boolean tutorialExpired;
	@Column(name = "tutorialLocked", nullable = true)
	private boolean tutorialLocked;
	@Column(name = "schedule", nullable = false)
	private Date schedule; // required
	@Column(name = "createTime", nullable = false)
	private Date createTime; // required
	@Column(name = "modifyTime", nullable = false)
	private Date modifyTime; // required
	@Column(name = "openDays", nullable = false)
	private int openDays; // required
	@Column(name = "lengthInMins", nullable = false)
	private int lengthInMins; // required
	@Version
	private Integer version;

	/**
	 * Default constructor - creates a new instance with no values set.
	 */
	public Tutorial() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getType() {
		return type;
	}

	public int getCategory() {
		return category;
	}

	public int getCost() {
		return cost;
	}

	public int getMethod() {
		return method;
	}

	public Set<User> getTutors() {
		return tutors;
	}

	public Set<User> getStudents() {
		return students;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public boolean getTutorialExpired() {
		return tutorialExpired;
	}

	public boolean getTutorialLocked() {
		return tutorialLocked;
	}

	public Date getSchedule() {
		return schedule;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public int getOpenDays() {
		return openDays;
	}

	public int getLengthInMins() {
		return lengthInMins;
	}

	public Integer getVersion() {
		return version;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public void setTutors(Set<User> tutors) {
		this.tutors = tutors;
	}

	public void setStudents(Set<User> students) {
		this.students = students;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public void setOpenDays(int openDays) {
		this.openDays = openDays;
	}

	public void setLengthInMins(int lengthInMins) {
		this.lengthInMins = lengthInMins;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setTutorialExpired(boolean tutorialExpired) {
		this.tutorialExpired = tutorialExpired;
	}

	public void setTutorialLocked(boolean tutorialLocked) {
		this.tutorialLocked = tutorialLocked;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Set<TutorialSchedule> getTutorialSchedules() {
		return tutorialSchedules;
	}

	public void setTutorialSchedules(Set<TutorialSchedule> tutorialSchedules) {
		this.tutorialSchedules = tutorialSchedules;
	}

	public int getMaxParticipate() {
		return maxParticipate;
	}

	public void setMaxParticipate(int maxParticipate) {
		this.maxParticipate = maxParticipate;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Tutorial)) {
			return false;
		}

		final Tutorial tutorial = (Tutorial) o;

		return !(name != null ? !name.equals(tutorial.getName()) : tutorial
				.getName() != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (name != null ? name.hashCode() : 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		final String STRING_SEPERATOR = "||";
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("name", this.name).append(
				STRING_SEPERATOR).append("description", this.description)
				.append(STRING_SEPERATOR).append("cost", this.cost).append(
						"maxParticipate", this.maxParticipate).append(
						STRING_SEPERATOR).append("schedule", this.schedule)
				.append(STRING_SEPERATOR).append("enabled", this.enabled);
		return sb.toString();
	}
}
