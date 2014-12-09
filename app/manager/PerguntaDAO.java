package manager;

import com.avaje.ebean.Ebean;

import models.Pergunta;

public class PerguntaDAO {

	public static Pergunta buscarPorNickName(String nikname){
		return Ebean.find(Pergunta.class).where().eq("nikname", nikname).findUnique();
	}
}
