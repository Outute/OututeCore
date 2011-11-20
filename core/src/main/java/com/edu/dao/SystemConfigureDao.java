/*******************************************************************************
 * Copyright (c) 2011-11-20 @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package com.edu.dao;

import org.appfuse.dao.GenericDao;

import com.edu.model.SystemConfigure;

/**
 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
 * @since 2011-11-20
 */
public interface SystemConfigureDao extends GenericDao<SystemConfigure, Long> {

	/**
	 * test if the system configure name is exists in db
	 * @param name
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-20
	 */
	public boolean exists(String name);

	/**
	 * get system configure by name
	 * @param name
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-20
	 */
	public SystemConfigure get(String name);
}
