package com.edu.service;

import com.edu.model.TimeSchedule;
import com.edu.model.Tutorial;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
@Path("/")
@Produces({"application/json", "application/xml"})
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
     * Retrieves a TimeSchedule by timeScheduleId.  An exception is thrown if TimeSchedule not found
     * @param timeScheduleId
     * @return
     * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
     * @since 2011-10-18
     */
    @GET
    @Path("/timeschedule")
    TimeSchedule getTimeSchedule(Long timeScheduleId);
    
    /**
     * Retrieves a list of all timeschedule by tutorialId.
     * @param tutorialId
     * @return
     * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
     * @since 2011-10-18
     */
    @GET
    @Path("/timeschedule")
    List<TimeSchedule> getAllTimeScheduleByTutorialId(Long tutorialId);
    
    /**
     * Saves a TimeSchedule infomation
     * @param timeSchedule
     * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
     * @since 2011-10-18
     */
    @POST
    @Path("/timeschedule")
    void saveTimeSchedule(TimeSchedule timeSchedule);
    
    /**
     * Removes a timeSchedule from the db by id
     * @param timeScheduleId
     * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
     * @since 2011-10-18
     */
    @DELETE
    @Path("/timeschedule")
    void removeTimeSchedule(Long timeScheduleId);
}
