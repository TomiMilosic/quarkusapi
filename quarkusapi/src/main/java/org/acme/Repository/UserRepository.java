package org.acme.Repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import org.acme.Models.User;

import java.util.List;


@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {

    public User findbyName(String name){
        return find("name", name).firstResult();
    }

    public List<User> FindOrderName(){
        return listAll(Sort.by("name"));
    }


}