package org.appfuse.service;

import org.appfuse.model.Tutorial;

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
}
