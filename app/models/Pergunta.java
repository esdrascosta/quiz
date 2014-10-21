package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Pergunta extends Model{

	/** serial version */
	private static final long serialVersionUID = -5740651158301395966L;

	@Id
	@GeneratedValue
	public long id;

	@Column(unique = true)
	public String nikname;
	
	public String pergunta;
}