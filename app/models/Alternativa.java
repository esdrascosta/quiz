package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class Alternativa extends Model {

	/** serial version */
	private static final long serialVersionUID = -3803023860872635662L;

	@Id
	@GeneratedValue
	public long id;
	
	public String descricao;
	
	public Boolean alternativaCerta;
	
	@ManyToOne
	public Pergunta pergunta;
}