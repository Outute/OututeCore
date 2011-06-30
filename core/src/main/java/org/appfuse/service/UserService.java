package org.appfuse.service;

import org.appfuse.model.User;

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
public interface UserService {
    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    @GET
    @Path("/user/{id}")
    User getUser(@PathParam("id") String userId);

    /**
     * Finds a user by their username.
     *
     * @param username the user's username used to login
     * @return User a populated user object
     */
    @GET
    @Path("/{username}")
    User getUserByUsername(@PathParam("username") String username);

    /**
     * Retrieves a list of all users.
     *
     * @return List
     */
    @GET
    @Path("/users")
    List<User> getUsers();

    /**
     * Saves a user's information
     *
     * @param user the user's information
     * @return updated user
     * @throws UserExistsException thrown when user already exists
     */
    @POST
    @Path("/user")
    User saveUser(User user) throws UserExistsException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    @DELETE
    @Path("/user")
    void removeUser(String userId);
}
