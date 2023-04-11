package org.acme.Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import javax.inject.Inject;

import java.util.List;
import java.util.Random;
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

    public String getRandomHexString(){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < 24){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, 24);
    }

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
        return userRepository.find("_id", id).firstResult();

    }

    @POST
    public User addUser(User user) {
        if(user==null){
            LOG.info("Empty:...");
            return null;
        }
        LOG.info("Creating user:...");
        userRepository.persist(user);
        return user;
    }

    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") String id, User user) {
        if(user==null){
            LOG.info("Empty:...");
            return null;
        }

        LOG.info("Updating user...");
        User entity = userRepository.find("_id", id).firstResult();
        entity.id= user.id;
        entity.name=user.name;
        entity.surname=user.surname;
        entity.reg_st=user.reg_st;
        userRepository.update(entity);
        return entity;
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") String id) {
        LOG.info("Deleting user...");
        var user= userRepository.find("_id", id).firstResult();
        userRepository.delete(user);
    }
}