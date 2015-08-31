package models;

import play.db.ebean.Model;

import javax.persistence.*;


import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Entity
public class User extends Model {

    @Id
    public Long id; // TODO integer primary key auto_increment,


    public String username; // TODO not null unique
    public String email; // TODO not null unique
    public String password; //TODO not null
    public Date firstEntry;// TODO time date
    public Date lastEntry; // TODO time date

    public static Finder<Long, User> find = new Finder(Long.class, User.class);

}

