package controllers;

import java.math.BigInteger;
import java.security.SecureRandom;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result random(){
    	SecureRandom random = new SecureRandom();
    	return ok(new BigInteger(130, random).toString(32));
    }
}
