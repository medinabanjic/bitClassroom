package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import models.*;
import views.html.users.*;

import java.util.List;
import play.data.Form;

import com.avaje.ebean.Ebean;
import play.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by boris.tomic on 01/09/15.
 */
public class Users extends Controller {

    private static final Form<User> userForm = Form.form(User.class);

    public Result index() {
        List<User> users = User.findAll();
        return ok("empty first page");
    }

    public Result log() {
        return ok(login.render(userForm));
    }

    public Result details() {
        return ok(email.render());
    }

    public Result login() {
        Form<User> boundForm = userForm.bindFromRequest();

        String email = boundForm.bindFromRequest().field("email").value();
        String password = boundForm.bindFromRequest().field("password").value();

        String passwordHashed = getEncriptedPasswordMD5(password);

        User user = User.findUserByEmailAndPassword(email, passwordHashed);


       // User toAdd = new User(email, passwordHashed);
       // Logger.info(toAdd.toString());
       // Ebean.save(toAdd);

        if (user != null) {
            return ok(register.render("Successful login"));
        } else {
            return redirect("login");
            //return notFound(String.format("User with %s email does not exists.", email));
        }

    }

    private String getEncriptedPasswordMD5(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes(), 0, password.length());
            String result = new BigInteger(1, md5.digest()).toString(16);
            md5.reset();
            return result;
        } catch (NoSuchAlgorithmException e) {
            // TODO add to logger
        }
        return "INVALID PASSWORD";
    }
}
