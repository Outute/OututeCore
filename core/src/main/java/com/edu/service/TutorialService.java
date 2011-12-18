package com.edu.service;

import com.edu.model.TutorialSchedule;
import com.edu.model.Tutorial;
import com.edu.model.TutorialScheduleStudent;
import com.edu.model.TutorialScheduleStudentKey;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.Date;
import java.util.List;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@javax.jws.WebService
@Path("/")
@Produces( { "application/json", "application/xml" })
public interface TutorialService {
	/**
	 * Retrieves a tutorial by tutorialId.  An exception is thrown if tutorial not found
	 *
	 * @param tutorialId the identifier for the tutorial
	 * @return Tutorial
	 */
	@GET
	@Path("/tutorial/{id}")
	Tutorial getTutorial(@PathParam("id") Long tutorialId);

	/**
	 * Retrieves a list of all tutorials.
	 *
	 * @return List
	 */
	@GET
	@Path("/tutorials")
	List<Tutorial> getTutorials();

	/**
	 * Saves a tutorial information
	 *
	 * @param tutorial the tutorial information
	 * @return updated tutorial
	 * @throws TutorialExistsException thrown when tutorial already exists
	 */
	@POST
	@Path("/tutorial")
	void saveTutorial(Tutorial tutorial) throws TutorialExistsException;

	/**
	 * Removes a tutorial from the database by their Id
	 *
	 * @param tutorialId the tutorial id
	 */
	@DELETE
	@Path("/tutorial")
	void removeTutorial(Long tutorialId);

	/**
	 * Retrieves a TutorialSchedule by tutorialScheduleId.  An exception is thrown if TutorialSchedule not found
	 * @param tutorialScheduleId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	@GET
	@Path("/tutorialSchedule")
	TutorialSchedule getTutorialSchedule(Long tutorialScheduleId);

	/**
	 * Retrieves a list of all tutorialSchedule by tutorialId.
	 * @param tutorialId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	@GET
	@Path("/tutorialSchedule")
	List<TutorialSchedule> getAllTutorialScheduleByTutorialId(Long tutorialId);

	/**
	 * Saves a TutorialSchedule infomation
	 * @param tutorialSchedule
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	@POST
	@Path("/tutorialSchedule")
	void saveTutorialSchedule(TutorialSchedule tutorialSchedule);

	/**
	 * Removes a tutorialSchedule from the db by id
	 * @param tutorialScheduleId
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	@DELETE
	@Path("/tutorialSchedule")
	void removeTutorialSchedule(Long tutorialScheduleId);

	/**
	 * find Tutorials by name or start date or tutor name
	 * @param id
	 * @param name
	 * @param start
	 * @param end
	 * @param tutorName
	 * @param category
	 * @param sortBy
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	@GET
	@Path("/tutorialSchedule")
	List<Tutorial> findTutorials(Long id, String name, Date start, Date end,
			String tutorName, Integer category, String sortBy);

	/**
	 * remove a user's TutorialSchedule by given tutorialScheduleId and userId
	 * @param tutorialScheduleId
	 * @param userId
	 * @param date
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	@DELETE
	@Path("/tutorialSchedule")
	void cancelTutorialSchedule(Long tutorialScheduleId, Long userId, Date date);

	/**
	 * remove an user's Tutorial by given tutorialId and userId
	 * @param tutorialId
	 * @param userId
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	@DELETE
	@Path("/tutorialSchedule")
	void cancelTutorial(Long tutorialId, Long userId);

	/**
	 * register class
	 * @param tutorialId
	 * @param ids
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	@POST
	@Path("/tutorialSchedule")
	void registerTutorial(Long tutorialId, TutorialScheduleStudentKey[] ids);

	/**
	 * find an user's tutorials
	 * @param userId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	@GET
	@Path("/tutorialSchedule")
	List<Tutorial> findTutorialsByUserId(Long userId);

	/**
	 * find an tutor's tutorials
	 * @param tutorId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-12-18
	 */
	@GET
	@Path("/tutorialSchedule/${tutorId}")
	List<Tutorial> findTutorialsByTutorId(@PathParam("tutorId")Long tutorId);

	/**
	 * find an user's tutorial schedule
	 * @param tutorialId
	 * @param userId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	@GET
	@Path("/tutorialSchedule")
	List<TutorialScheduleStudent> findTutorialSchedulesByUserId(
			Long tutorialId, Long userId);

	/**
	 * find student's tutorial schedule between start and end date.
	 * @param studentId
	 * @param start
	 * @param end
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-27
	 */
	@GET
	@Path("/tutorialSchedule")
	List<TutorialScheduleStudent> findTutorialSchedulesByStudentIdAndDate(
			Long studentId, Date start, Date end);

	/**
	 * find TutorialSchedule by date between start and end
	 * @param userId
	 * @param start
	 * @param end
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-28
	 */
	@GET
	@Path("/tutorialSchedule")
	List<TutorialSchedule> findStudentTutorialSchedule(Long userId, Date start,
			Date end);

	/**
	 * find tutor's TutorialSchedule by date between start and end
	 * @param tutorId
	 * @param start
	 * @param end
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-27
	 */
	@GET
	@Path("/tutorialSchedule")
	List<TutorialSchedule> findTutorTutorialSchedule(Long tutorId, Date start,
			Date end);

	/**
	 * find current active tutorials
	 * @param name
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-3
	 */
	@GET
	@Path("/tutorials")
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
	@GET
	@Path("/tutorials")
	List<Tutorial> findHistoryTutorials(int pageSize, int currentPage,
			String name);
}
