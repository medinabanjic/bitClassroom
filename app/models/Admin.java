package models;

public class Admin extends Model {

    public Long id; // TODO integer primary key auto_increment,
    public String username; // TODO not null unique
    public String email; // TODO not null unique
    public String password; //TODO not null
    public Date firstEntry;// TODO time date
    public Date lastEntry; // TODO time date

    public static Finder<Long, Admin> find = new Finder(Long.class, Admin.class);
}

