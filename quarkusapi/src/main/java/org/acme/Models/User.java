package org.acme.Models;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;

@MongoEntity(collection = "User")
public class User  {

    @BsonId
    public String _id;
    public String name;
    public String surname;
    public String reg_st;


    public User() {

    }

    public User(String id, String name, String surname, String registrationState) {
        this._id = id;
        this.name = name;
        this.surname = surname;
        this.reg_st = registrationState;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + _id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", registrationState='" + reg_st + '\'' +
                '}';
    }
}