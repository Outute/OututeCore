package com.edu.service;

import org.appfuse.service.GenericManager;

import com.edu.model.TutorialSchedule;
import com.edu.model.Tutorial;
import com.edu.model.User;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author Reid
 */
public interface TutorialManager extends GenericManager<Tutorial, Long> {
	/**
	 * {@inheritDoc}
	 */
	List<Tutorial> getTutorials();

	/**
	 * {@inheritDoc}
	 */
	Tutorial getTutorial(Long tutorialId);

	/**
	 * {@inheritDoc}
	 */
	void saveTutorial(Tutorial tutorial) throws TutorialExistsException;

	/**
	 * {@inheritDoc}
	 */
	void removeTutorial(Long tutorialId);

	/**
	 * Search a user for search terms.
	 * @param searchTerm the search terms.
	 * @return a list of matches, or all if no searchTerm.
	 */
	List<Tutorial> search(String searchTerm);

	/**
	 * {@inheritDoc}
	 */
	User getTutorialByTutorName(String tutorName)
			throws TutorialNotFoundException;

	/**
	 * Retrieves a TutorialSchedule by tutorialScheduleId.  An exception is thrown if TutorialSchedule not found
	 * @param tutorialScheduleId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	TutorialSchedule getTutorialSchedule(Long tutorialScheduleId);

	/**
	 * Retrieves a list of all tutorialSchedule by tutorialId.
	 * @param tutorialId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	List<TutorialSchedule> getAllTutorialScheduleByTutorialId(Long tutorialId);

	/**
	 * Saves a TutorialSchedule infomation
	 * @param tutorialSchedule
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	void saveTutorialSchedule(TutorialSchedule tutorialSchedule);

	/**
	 * Removes a tutorialSchedule from the db by id
	 * @param tutorialScheduleId
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	void removeTutorialSchedule(Long tutorialScheduleId);

	/**
	* find Tutorials by name or start date or tutor name
	* @param name
	* @param start
	* @param end
	* @param tutorName
	* @param sortBy
	* @return
	* @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	* @since 2011-10-24
	*/
	List<Tutorial> findTutorials(Long id, String name, Date start, Date end,
			String tutorName, String sortBy);

	/**
	 * remove an user's TutorialSchedule by given tutorialScheduleId and userId
	 * @param tutorialScheduleId
	 * @param userId
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	void cancelTutorialSchedule(Long tutorialScheduleId, Long userId);

	/**
	 * remove an user's Tutorial by given tutorialId and userId
	 * @param tutorialId
	 * @param userId
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	void cancelTutorial(Long tutorialId, Long userId);

	/**
	 * register class
	 * @param tutorialId
	 * @param tutorialScheduleIds
	 * @param userId
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	void registerTutorial(Long tutorialId, Long[] tutorialScheduleIds,
			Long userId);

	/**
	 * find an user's tutorials
	 * @param userId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	List<Tutorial> findTutorialsByUserId(Long userId);

	/**
	 * find an user's tutorial schedule
	 * @param tutorialId
	 * @param userId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	List<TutorialSchedule> findTutorialSchedulesByUserId(Long tutorialId,
			Long userId);

	/**
	 * find TutorialSchedule by date between start and end
	 * @param start
	 * @param end
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-28
	 */
	List<TutorialSchedule> findTutorialSchedule(Date start, Date end);
}