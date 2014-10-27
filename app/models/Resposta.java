package models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
@IdClass(RespostaID.class)
public class Resposta extends Model {

	/** serial version */
	private static final long serialVersionUID = -7659887923541176564L;

	@EmbeddedId
	public RespostaID respostaID;
	
	@ManyToOne
	@JoinColumn(name="pergunta_id", referencedColumnName = "id", insertable = false, updatable = false)
	public Pergunta pergunta;
	
	@ManyToOne
	@JoinColumn(name="alternativa_id", referencedColumnName = "id",insertable = false, updatable = false)
	public Alternativa alternativa;
}