package com.edu.dao;

import org.appfuse.dao.GenericDao;
import org.springframework.transaction.annotation.Transactional;

import com.edu.model.Tutorial;
import com.edu.service.TutorialNotFoundException;

import java.util.Date;
import java.util.List;

/**
 * User Data Access Object (GenericDao) interface.
 *
 * @author Reid
 */
public interface TutorialDao extends GenericDao<Tutorial, Long> {

	/**
	 * Gets tutorials based on name.
	 * @param tutorname the tutor's name
	 * @return List list of tutorials
	 * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when user not
	 * found in database
	 */
	@Transactional
	List<Tutorial> loadTutorialsByName(String name)
			throws TutorialNotFoundException;

	/**
	 * Gets a list of tutorials ordered by the uppercase version of their name.
	 *
	 * @return List list of tutorials
	 */
	List<Tutorial> getTutorials();

	/**
	 * Saves a tutorial.
	 * @param tutorial the object to be saved
	 * @return the persisted Tutorial object
	 */
	Tutorial saveTutorial(Tutorial tutorial);

	/**
	 * find Tutorials by name or start date or tutor name
	 * @param name
	 * @param start
	 * @param end
	 * @param tutorName
	 * @param category
	 * @param sortBy
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-24
	 */
	List<Tutorial> findTutorials(String name, Date start, Date end,
			String tutorName, Integer category, String sortBy);

	/**
	 * find an user's tutorials
	 * @param userId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	List<Tutorial> findTutorialsByUserId(Long userId);
	
	/**
	 * find an tutor's tutorials
	 * @param tutorId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-12-18
	 */
	List<Tutorial> findTutorialsByTutorId(Long tutorId);

	/**
	 * find current active tutorials
	 * @param name
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-3
	 */
	List<Tutorial> findCurrentTutorials(int pageSize, int currentPage,
			String name);

	/**
	 * find history tutorials
	 * @param name
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-3
	 */
	List<Tutorial> findHistoryTutorials(int pageSize, int currentPage,
			String name);
}
