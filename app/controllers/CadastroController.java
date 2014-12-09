package controllers;

import java.math.BigInteger;
import java.security.SecureRandom;

import models.Pergunta;

import org.apache.commons.lang3.StringUtils;

import com.avaje.ebean.Ebean;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.cadastro;
public class CadastroController extends Controller {


	private static final Form<Pergunta> CADASTRO_FORM = Form.form(Pergunta.class);
	 
	public static Result pergutaSalva(String nomePergunta){
		return ok(views.html.pergunta_salva.render(nomePergunta));
	}
	
	public static Result cadastro(){
		return ok(cadastro.render(CADASTRO_FORM));
	}
	
	public static Result submit(){
		Form<Pergunta> fromRequest = CADASTRO_FORM.bindFromRequest();
		
		if(fromRequest.hasErrors()){
			return badRequest(cadastro.render(fromRequest));
		}else{
			Pergunta pergunta = fromRequest.get();
			if(StringUtils.isEmpty(pergunta.nikname)){
				SecureRandom random = new SecureRandom();
		    	String nickName = new BigInteger(130, random).toString(32);
		    	pergunta.nikname = nickName;
			}
			Ebean.save(pergunta);
			return redirect(routes.CadastroController.pergutaSalva(pergunta.nikname));
		}
	}
}