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

    private Long id;
    private String name;                        // required
    private String description;
    private int type;                        // required
    private int category;                    // required
    private int cost;                   // required
    private int method;                    // required
    //private Long id;
    private Set<User> tutors;                       // required
    private Set<User> students;                    // required
    private boolean enabled;
    private boolean tutorialExpired;
    private boolean tutorialLocked;
    private Date schedule;                    // required
    private Date createTime;                    // required
    private Date modifyTime;                    // required
    private int openDays;                    // required
    private int lengthInMins;                    // required
    private Integer version;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Tutorial() {
    }

    /**
     * Create a new instance and set the tutorial name.
     *
     * @param tutorial name.
     */
    public Tutorial(String name, String description, int type, int category, int cost, int method,
    Date schedule, Date createTime, Date modifyTime, int openDays, int lengthInMins, Set<User> tutors, Set<User> students) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.category = category;
        this.cost = cost;
        this.method = method;
        this.tutors = tutors;
        this.students = students;
        this.schedule = schedule;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.openDays = openDays;
        this.lengthInMins = lengthInMins;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SearchableId
    public Long getId() {
        return id;
    }

    @Column(name = "name", nullable = false, length = 50, unique = true)
    @SearchableProperty
    public String getName() {
        return name;
    }

    @Column(name = "description", length = 256)
    @SearchableProperty
    public String getDescription() {
        return description;
    }

    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    @Column(name = "category", nullable = false)
    public int getCategory() {
        return category;
    }

    @Column(name = "cost", nullable = false)
    public int getCost() {
        return cost;
    }

    @Column(name = "method", nullable = false)
    public int getMethod() {
        return method;
    }

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "tutorial_tutor", joinColumns = { @JoinColumn(name = "tutorial_id") },
      inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public Set<User> getTutors() {
        return tutors;
    }

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "tutorial_student", joinColumns = { @JoinColumn(name = "tutorial_id") },
      inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public Set<User> getStudents() {
        return students;
    }

    @Column(name = "enabled", nullable = false)
    public boolean getEnabled() {
        return enabled;
    }

    @Column(name = "tutorialExpired", nullable = true)
    public boolean getTutorialExpired() {
        return tutorialExpired;
    }

    @Column(name = "tutorialLocked", nullable = true)
    public boolean getTutorialLocked() {
        return tutorialLocked;
    }

    @Column(name = "schedule", nullable = false)
    public Date getSchedule() {
        return schedule;
    }

    @Column(name = "createTime", nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    @Column(name = "modifyTime", nullable = false)
    public Date getModifyTime() {
        return modifyTime;
    }

    @Column(name = "openDays", nullable = false)
    public int getOpenDays() {
        return openDays;
    }

    @Column(name = "lengthInMins", nullable = false)
    public int getLengthInMins() {
        return lengthInMins;
    }

    @Version
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

        return !(name != null ? !name.equals(tutorial.getName()) : tutorial.getName() != null);

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
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("name", this.name)
                .append(STRING_SEPERATOR)
                .append("description", this.description)
                .append(STRING_SEPERATOR)
                .append("cost", this.cost)
                .append(STRING_SEPERATOR)
                .append("schedule", this.schedule)
                .append(STRING_SEPERATOR)
                .append("enabled", this.enabled);
/**
        if (tutors != null) {
            sb.append("Tutors: ");

            int i = 0;
            for (User user : tutors) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(user.toString());
                i++;
            }
        } else {
            sb.append("No tutor found");
        }**/
        return sb.toString();
    }
}
