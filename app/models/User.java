package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.Logger;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

import java.lang.String;
import java.lang.Long;

@Entity
public class User extends Model {

    private static List<User> users = new ArrayList<User>();

<<<<<<< HEAD
    static {
        users = new ArrayList<User>();
        
    }
=======
    public static Finder<String, User> finder = new Finder<String, User>(String.class, User.class);
>>>>>>> 89cd2cce8d46a0a6b105b277b11272f75599ae7c

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(length = 50)
    public String email;

    @Column(length = 50)
    public String password;

    //public String firstName; // TODO not null unique
    //public String lastName;
    //public Date firstEntry;// TODO time date
    //public Date lastEntry; // TODO time date

    /**
     * Default empty constructor
     */
    public User() {

    }

    /**
     * Constructor for creating object
     * @param email
     * @param password
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static List<User> findAll() {
        List<User> results = new ArrayList<User>(finder.all());

        List<User> user = finder.where().eq("password", "4dbf44c6b1be736ee92ef90090452fc2").findList();
        Logger.info(user.toString());

        return results;
    }

    public static User findUserByEmailAndPassword(String email, String password) {
        List<User> user = finder.where().eq("email", email).eq("password", password).findList();
        if (user.size() == 0) {
            return null;
        }
        return (User) user.get(0);
    }

    public static User findById(Integer id) {
        for (User candidate : users) {
            if(candidate.id.equals(id)) {
                return candidate;
            }
        }
        return null;
    }

    public static boolean remove(User user) {
        return users.remove(user);
    }

    public String toString() {
        return String.format("User email: %s", email);
    }






}

