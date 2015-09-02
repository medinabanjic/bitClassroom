package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.avaje.ebean.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.Model;
import java.lang.*;

@Entity
public class User extends Model {

    private static List<User> users;

    static {
        users = new ArrayList<User>();
        
    }

    @Id
    public Integer id; // TODO integer primary key auto_increment,

    public String email; // TODO not null unique

    public String password; //TODO not null


    //public String username; // TODO not null unique
    //public Date firstEntry;// TODO time date
    //public Date lastEntry; // TODO time date

    /**
     * Default empty constructor
     */
    public User() {

    }

    public User(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /**
     * example only
     * @param user
     */
    public static void saveToList(User user) {
        users.add(user);
    }

    public static List<User> findAll() {
        return new ArrayList<User>(users);
    }

    public static User findById(Integer id) {
        for (User candidate : users) {
            if(candidate.id.equals(id)) {
                return candidate;
            }
        }
        return null;
    }

    public static List<User> findByEmail(String email) {
        final List<User> results = new ArrayList<User>();
        for (User candidate : users) {
            if (candidate.email.toLowerCase().contains(email.toLowerCase())) {
                results.add(candidate);
            }
        }
        return results;
    }

    public static boolean remove(User user) {
        return users.remove(user);
    }

    public String toString() {
        return String.format("%d - %s", id, email);
    }


    //public static Finder<Long, User> find = new Finder(Long.class, User.class);

}

