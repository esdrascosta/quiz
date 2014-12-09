package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import play.data.validation.ValidationError;

import com.avaje.ebean.Ebean;

@Entity
@IdClass(RespostaID.class)
public class Resposta{

	@Id
	public long perguntaId;
	
	@Id
	public long alternativaId;

	@Id
	@NotNull
	public String nicknameUser;
	
	@ManyToOne
	@JoinColumn(name="pergunta_id", referencedColumnName = "id", insertable = false, updatable = false)
	public Pergunta pergunta;
	
	@ManyToOne
	@JoinColumn(name="alternativa_id", referencedColumnName = "id",insertable = false, updatable = false)
	public Alternativa alternativa;
	
	public List<ValidationError> validate(){
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		Resposta resposta = Ebean.find(Resposta.class).where().eq("nicknameUser", this.nicknameUser).findUnique();
		
		if(resposta != null){
			errors.add (  new ValidationError ( "Apelido Usuario" , "O apelido para o osuario já existe"));
		}
		
		return errors.isEmpty () ? null : errors ;
	}

	public long getPerguntaId() {
		return perguntaId;
	}

	public void setPerguntaId(long perguntaId) {
		this.perguntaId = perguntaId;
	}

	public long getAlternativaId() {
		return alternativaId;
	}

	public void setAlternativaId(long alternativaId) {
		this.alternativaId = alternativaId;
	}

	public String getNicknameUser() {
		return nicknameUser;
	}

	public void setNicknameUser(String nicknameUser) {
		this.nicknameUser = nicknameUser;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	@Override
	public String toString() {
		return "Resposta [perguntaId=" + perguntaId + ", alternativaId="
				+ alternativaId + ", nicknameUser=" + nicknameUser + "]";
	}
}