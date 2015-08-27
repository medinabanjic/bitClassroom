package models;

public class Admin extends Model {

    public Long id;
    public String username;
    public String email;
    public String password;

    public static Finder<Long, Admin> find = new Finder(Long.class, Admin.class);
}

