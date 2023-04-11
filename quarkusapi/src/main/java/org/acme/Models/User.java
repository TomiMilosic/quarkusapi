package org.acme.Models;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;

//@MongoEntity(collection = "User")
public class User extends PanacheMongoEntity {


    public String id;
    public String name;
    public String surname;
    public String reg_st;


    public User() {

    }

    public User( String id,String name, String surname, String registrationState) {
        this.id=id;
        this.name = name;
        this.surname = surname;
        this.reg_st = registrationState;
    }


}