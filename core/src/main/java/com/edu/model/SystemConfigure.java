/*******************************************************************************
 * Copyright (c) 2011-11-20 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.appfuse.model.BaseObject;

import com.edu.util.DateUtil;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-11-20
 */
@Entity
@Table(name = "system_configure")
@javax.xml.bind.annotation.XmlRootElement
public class SystemConfigure extends BaseObject implements Serializable {

	private static final long serialVersionUID = -4811622689943400475L;
	public static final String NOTIFICATION_DUETIME = "NOTIFICATION_DUETIME_MINUTE";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Column(name = "value", nullable = false)
	private String value;

	public SystemConfigure() {
	}

	public SystemConfigure(Long id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	@Transient
	public Integer getIntegerValue() {
		try {
			return Integer.valueOf(value.trim());
		} catch (Exception e) {
		}
		return null;
	}

	@Transient
	public Long getLongValue() {
		try {
			return Long.valueOf(value.trim());
		} catch (Exception e) {
		}
		return null;
	}

	@Transient
	public Date getDateValue() {
		try {
			return DateUtil.convertStringToDate(value);
		} catch (Exception e) {
		}
		return null;
	}

	//getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int hashCode() {
		return new HashCodeBuilder().append(name).toHashCode();
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
