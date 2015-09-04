package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import models.*;

import utility.MD5PasswordHash;
import views.html.users.login;
import views.html.users.email;
import views.html.users.register;
import views.html.users.addUser;

import java.util.Arrays;
import java.util.List;
import play.data.Form;

import com.avaje.ebean.Ebean;
import play.Logger;

import utility.MD5PasswordHash;

/**
 * Created by boris.tomic on 01/09/15.
 */
public class Users extends Controller {

    private static final Form<User> userForm = Form.form(User.class);

    private static boolean canAccess = false;

    /**
     * Renders login page with userForm
     *
     * @return
     */
    public Result login() {
        return ok(login.render(userForm));
    }

    /**
     * Renders popup email form
     *
     * @return
     */
    public Result sendEmail() {
        return ok(email.render());
    }

    /**
     * Checks login, reads from userForm all the values eg. email and password,
     * sends both to database for validation. If everything goes well user is
     * redirected to new page, otherwise user is redirected to login.
     * TODO validation
     *
     * @return
     */
    public Result checkLogin() {

        Form<User> boundForm = userForm.bindFromRequest();

        if (boundForm.hasErrors()) {
            flash("warning", "Please correct the form.");
            return redirect("login");
        }

        String email = boundForm.bindFromRequest().field("email").value();
        String password = boundForm.bindFromRequest().field("password").value();

        String passwordHashed = MD5PasswordHash.getEncriptedPasswordMD5(password);

        User user = User.findUserByEmailAndPassword(email, passwordHashed);

        Logger.info(email + " " + password + " " + passwordHashed);
        if (user != null) {
            canAccess = true;
            flash("success", String.format("User %s successfully logged in", user));
            return ok(register.render("Successful login"));
        } else {
            flash("warning", "User not found under provided email and password.");
            return ok(login.render(boundForm));
        }
    }

    /**
     * Renders regiser page with userForm, page is used to add new user to database
     *
     * @return
     */
    public Result register() {
        if (canAccess) {
            return ok(addUser.render(userForm));
        }
        return notFound("Can't access required site. Please login in to proceed.");
    }

    /**
     * Reads email and password from userForm, and sends data to database.
     * TODO validation
     *
     * @return
     */
    public Result saveUser() {
        Form<User> boundForm = userForm.bindFromRequest();

        if (boundForm.hasErrors()) {
            flash("warning", "Please correct the form.");
            return redirect("register");
        }

        String email = boundForm.bindFromRequest().field("email").value();
        String password = boundForm.bindFromRequest().field("password").value();
        String passwordHashed = MD5PasswordHash.getEncriptedPasswordMD5(password);

        if (isValid(email) && isValidPassword(password)) {
            User user = new User(email, passwordHashed);


            if (user != null) {
                Ebean.save(user);

                Logger.info(user.toString());

                flash("success", String.format("User %s successfully added to database", user));
                return ok(addUser.render(userForm));
            } else {
                flash("warning", "Can not add user with provided email and password to database.");
                return redirect("/");

            }
        } else {

            flash("warning", "Entered e-mail address is incorrect!");
            return ok(addUser.render(userForm));

        }
    }


    /**
     * Checks if email is valid
     * TODO add more comments
     * TODO changename ofmethod
     * @param email
     * @return
     */
    public Boolean isValid(String email) {

        if (email.contains("@") && email.contains(".") && email.substring(email.indexOf("@"), email.length()).equals("@bitcamp.ba")) {

            return true;

        } else {

            return false;
        }

    }

    /**
     * TODO fix return
     * @param password
     * @return
     */
    public Boolean isValidPassword(String password) {
        char passOne[] = new char[password.length()];
        for (int i = 0; i < password.length(); i++) {
            passOne[i] = password.charAt(i);
        }
        Logger.info(Arrays.toString(passOne));

        for (int i = 0; i < passOne.length; i++) {
            if (passOne[i] >= 33 && passOne[i] <= 126) {
                return true;
            }
        }
        return false;
    }



}
