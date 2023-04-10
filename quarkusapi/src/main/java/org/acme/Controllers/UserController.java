package org.acme.Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

import io.quarkus.panache.common.Sort;
import org.acme.Models.User;
import org.acme.Repository.UserRepository;
import org.bson.types.ObjectId;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    private static final Logger LOG = Logger.getLogger("User controller");

    @Inject
    UserRepository userRepository;

    @GET
    public List<User> getUsers() {
        LOG.info("Getting all users...");
        return userRepository.listAll(Sort.by("name"));
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") String id) {
        LOG.info("Getting user by id...");
        return userRepository.findById(new ObjectId(id));

    }

    @POST
    public User addUser(User user) {
        LOG.info("Creating user:...");
        userRepository.persist(user);
        return user;
    }

    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") String id, User user) {
        LOG.info("Updating user...");
        User entity = userRepository.findById(new ObjectId(id));
        entity.name=user.name;
        entity.surname=user.surname;
        entity.reg_st=user.reg_st;
        userRepository.persist(entity);
        return entity;
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") String id) {
        LOG.info("Deleting user...");
        userRepository.deleteById(new ObjectId(id));
    }
}