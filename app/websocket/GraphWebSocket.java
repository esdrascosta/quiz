package websocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import manager.ResultadoDAO;
import play.libs.Json;
import play.mvc.WebSocket;
import akka.actor.UntypedActor;

import com.fasterxml.jackson.databind.JsonNode;

import dto.QuantidadeRespostaDTO;

public class GraphWebSocket extends UntypedActor{
	
	/**
	 * Clientes registrados
	 */
	Map<String,WebSocket.Out<JsonNode>> resgistred = new HashMap<String, WebSocket.Out<JsonNode>>();
	
	@Override
	public void onReceive(Object mensagem) throws Exception {
		
		if(mensagem instanceof MensagemRegistro){
			
			MensagemRegistro msg = (MensagemRegistro) mensagem;
			resgistred.put(msg.id, msg.channel);
			
		}else if(mensagem instanceof MensagemDesresgistro){
			
			MensagemDesresgistro msg = (MensagemDesresgistro) mensagem;
			resgistred.remove(msg.id);
			
		}else if( mensagem instanceof MensagemResultado){
			
			MensagemResultado msg = (MensagemResultado) mensagem;
			String nickname = msg.nickName;
			
			List<QuantidadeRespostaDTO> respostas = ResultadoDAO.respostas(nickname);

			JsonNode node = Json.toJson(respostas);
			
			resgistred.forEach((uuid,chanel) -> chanel.write(node));
		}
	}

	
	public static class MensagemRegistro{
		public String id;
		public WebSocket.Out<JsonNode> channel;

		public MensagemRegistro(String id, WebSocket.Out<JsonNode> channel) {
			super();
			this.id = id;
			this.channel = channel;
		}
	}
	
	public static class MensagemDesresgistro{
		public String id;

		public MensagemDesresgistro(String id) {
			super();
			this.id = id;
		}
	}
	
	public static class MensagemResultado{
		public String nickName;

		public MensagemResultado(String nickName) {
			super();
			this.nickName = nickName;
		}
	}
}