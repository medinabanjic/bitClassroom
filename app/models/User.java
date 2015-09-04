package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Class User extends ebean Model, represents table User in database.
 * Finder is created alon with CRUD methods for easier manipulation of data.
 */
@Entity
public class User extends Model {

    public static Finder<String, User> finder = new Finder<String, User>(String.class, User.class);

    private static List<User> users = new ArrayList<User>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    @Column(name = "email", unique = true, updatable = false, length = 100)
    public String email;

    @Column(name = "password", length = 100)
    public String password;

    @Column(name = "name", length = 100)
    public String name;

    @Column(name = "lastName", length = 100)
    public String lastName;

    @Column(name ="role", length = 1)
    public Integer role;

    @Column(name ="yearOfBirth")
    public Date yearOfBirth;

    @Column(name ="gender")
    public Integer gender;

    @Column(name="skypeName", length = 100)
    public String skypeName;

    @Column(name="facebookProfile" ,length = 300)
    public String facebookProfile;


    //public Date firstEntry;// TODO timedate
    //public Date lastEntry; // TODO timedate

    /**
     * Default empty constructor
     */
    public User() {

    }

    /**
     * Constructor for creating object with email and password only. Other values are not included.
     *
     * @param email    <code>String</code> type value of User email
     * @param password <code>String</code> type value of User password
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor for creating object with all attributes, used for update table for edit your personal info
     * @param facebookProfile String of faceboook profile link
     * @param skypeName String skype name (nickname)
     * @param gender  Integer value: 1 is M 0 is F
     * @param yearOfBirth Date of birth
     * @param role Boolean
     * @param lastName String value of last name
     * @param name  String value of first name
     * @param password String of password
     * @param email String of email address
     */
    public User(String facebookProfile, String skypeName, Integer gender, Date yearOfBirth, Integer role, String lastName, String name, String password, String email) {
        this.facebookProfile = facebookProfile;
        this.skypeName = skypeName;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
        this.role = role;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    /**
     * Goes thru entire User table and finds every entry. Returns as ArrayList of Users.
     *
     * @return <code>ArrayList</code> of all Users from User table
     */
    public static List<User> findAll() {
        return new ArrayList<User>(finder.all());
    }

    /**
     * Searches for user email and password, both must match to get result.
     * If user and password don't match return is null, otherwise you get User object
     *
     * @param email    <code>String</code> type value of User email to look in database
     * @param password <code>String</code> type value of User password to look in database
     * @return <code>User</code> object if email and password match in table User, null if nothing is found.
     */
    public static User findUserByEmailAndPassword(String email, String password) {
        List<User> user = finder.where().eq("email", email).eq("password", password).findList();
        if (user.size() == 0) {
            return null;
        }
        return (User) user.get(0);
    }

    /**
     * Searches for user id in User table, if id is found, returns User object, otherwise returns null.
     *
     * @param id <code>Long</code> type value of user id to look in database
     * @return <code>User</code> object if id is found in database
     */
    public static User findUserById(Long id) {
        List<User> user = finder.where().eq("id", id).findList();
        if (user.size() == 0) {
            return null;
        }
        return (User) user.get(0);
    }

    /**
     * Method for check user role
     * @param role
     * @return
     */
    public static User findUserByRole( Integer role){

        List<User> user = finder.where().eq("role", role ).findList();
        if(user.size() == 0){

            return null;
        }
        return (User) user.get(0);
    }

    /**
     * Removes user from ArralList of users.
     *
     * @param user <code>User</code> type value of User object
     * @return <code>boolean</code> type value true if user is removed, false if not
     */
    public static boolean remove(User user) {

        return users.remove(user);
    }

    /**
     * toString method returns user email.
     *
     * @return <code>String</code> type value of user email
     */
    public String toString() {

        return String.format("%s", email);
    }


}

