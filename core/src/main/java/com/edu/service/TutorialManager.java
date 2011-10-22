package com.edu.service;

import org.appfuse.service.GenericManager;

import com.edu.model.TimeSchedule;
import com.edu.model.Tutorial;
import com.edu.model.User;

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
	public User getTutorialByTutorName(String tutorName)
			throws TutorialNotFoundException;

	/**
	 * Retrieves a TimeSchedule by timeScheduleId.  An exception is thrown if TimeSchedule not found
	 * @param timeScheduleId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	TimeSchedule getTimeSchedule(Long timeScheduleId);

	/**
	 * Retrieves a list of all timeschedule by tutorialId.
	 * @param tutorialId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	List<TimeSchedule> getAllTimeScheduleByTutorialId(Long tutorialId);

	/**
	 * Saves a TimeSchedule infomation
	 * @param timeSchedule
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	void saveTimeSchedule(TimeSchedule timeSchedule);

	/**
	 * Removes a timeSchedule from the db by id
	 * @param timeScheduleId
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	void removeTimeSchedule(Long timeScheduleId);
}
