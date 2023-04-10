package org.acme.Repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import org.acme.Models.User;
import org.bson.types.ObjectId;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepositoryBase<User, ObjectId> {
}