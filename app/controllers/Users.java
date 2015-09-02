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

    public Result list() {
        List<User> users = User.findAll();
        return ok(list.render(users));
    }

    public Result newUser() {
        return ok(details.render(userForm));
    }

    public Result details(Integer id) {
        return TODO;
    }

    public Result save() {
        Form<User> boundForm = userForm.bindFromRequest();
        String stringId = boundForm.bindFromRequest().field("id").value();
        String email = boundForm.bindFromRequest().field("email").value();
        String password = boundForm.bindFromRequest().field("password").value();

        Integer id = Integer.parseInt(stringId);

        String passwordHashed = getEncriptedPasswordMD5(password);

        User user = new User(id, email, passwordHashed);
        Logger.info(user.toString());

        User.saveToList(user);

        Ebean.save(user);

        return redirect(routes.Users.list());

    }

    private String getEncriptedPasswordMD5(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes(), 0, password.length());
            String result = new BigInteger(1, md5.digest()).toString(16);
            md5.reset();
            return result;
        } catch (NoSuchAlgorithmException e) {
            Logger.error("Could not encript password", e.getMessage());
        }
        return "INVALID PASSWORD";
    }
}
