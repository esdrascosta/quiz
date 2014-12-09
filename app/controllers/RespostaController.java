package controllers;

import java.util.List;
import java.util.UUID;

import manager.PerguntaDAO;
import manager.ResultadoDAO;
import models.Pergunta;
import models.Resposta;
import play.data.Form;
import play.libs.Akka;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import play.mvc.WebSocket.In;
import play.mvc.WebSocket.Out;
import websocket.GraphWebSocket;
import websocket.GraphWebSocket.MensagemDesresgistro;
import websocket.GraphWebSocket.MensagemRegistro;
import websocket.GraphWebSocket.MensagemResultado;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import views.html.*;
import dto.QuantidadeRespostaDTO;

public class RespostaController extends Controller {

	static ActorRef actor = Akka.system().actorOf(Props.create(GraphWebSocket.class));

	private static final Form<Resposta> RESPOSTA_FORM = Form.form(Resposta.class);

	public static Result quiz(String name) {

		Pergunta pergunta = PerguntaDAO.buscarPorNickName(name);
		if (pergunta == null) {
			return badRequest("pergunta não existe");
		}

		return ok(quiz.render(RESPOSTA_FORM, pergunta));
	}

	public static Result submit() {
		Form<Resposta> bindFromRequest = RESPOSTA_FORM.bindFromRequest();
		if (bindFromRequest.hasErrors()) {
			return badRequest("Não foi possivel registrar sua resposta verifique se o Apelido Usuario foi informado");
		}

		Resposta resposta = bindFromRequest.get();
		Ebean.save(resposta);

		notifiqueATodos(resposta.getPergunta().getNikname());

		return redirect(routes.RespostaController.resultado(resposta
				.getPergunta().getNikname()));
	}

	public static Result resultado(String nickNamePergunta) {

		if (PerguntaDAO.buscarPorNickName(nickNamePergunta) != null) {
			return ok(views.html.resultado.render(nickNamePergunta));
		} else {
			return badRequest("Pergunta não existe");
		}

	}

	public static Result respostasJson(String perguntaNickName) {

		List<QuantidadeRespostaDTO> respostas = ResultadoDAO
				.respostas(perguntaNickName);

		JsonNode node = Json.toJson(respostas);

		return ok(node);
	}

	private static void notifiqueATodos(String nickName) {
		actor.tell(new MensagemResultado(nickName), ActorRef.noSender());
	}

	private static void register(String uid, final In<JsonNode> in,
			final Out<JsonNode> out) {

		actor.tell(new MensagemRegistro(uid, out), ActorRef.noSender());

		in.onClose(() -> {
			actor.tell(new MensagemDesresgistro(uid), ActorRef.noSender());
		});

	}

	public static WebSocket<JsonNode> webInfo() {

		return WebSocket.whenReady((in, out) -> {
				register(UUID.randomUUID().toString(), in, out);
			});
	}
}