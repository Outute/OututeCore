package com.edu.service;

import com.edu.model.TutorialSchedule;
import com.edu.model.Tutorial;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
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
	 * @param sortBy
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	@GET
	@Path("/tutorialSchedule")
	List<Tutorial> findTutorials(Long id, String name, Date start, Date end,
			String tutorName, String sortBy);

	/**
	 * remove a user's TutorialSchedule by given tutorialScheduleId and userId
	 * @param tutorialScheduleId
	 * @param userId
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	@DELETE
	@Path("/tutorialSchedule")
	void cancelTutorialSchedule(Long tutorialScheduleId, Long userId);

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
	 * @param tutorialScheduleIds
	 * @param userId
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	@POST
	@Path("/tutorialSchedule")
	void registerTutorial(Long tutorialId, Long[] tutorialScheduleIds,
			Long userId);

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
	 * find an user's tutorial schedule
	 * @param tutorialId
	 * @param userId
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	@GET
	@Path("/tutorialSchedule")
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
	@GET
	@Path("/tutorialSchedule")
	List<TutorialSchedule> findTutorialSchedule(Date start, Date end);
}