package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Pergunta extends Model{

	/** serial version */
	private static final long serialVersionUID = -5740651158301395966L;

	public Pergunta(){
		alternativas = new ArrayList<Alternativa>(4);
	}
	
	@Id
	@GeneratedValue
	public long id;

	@Column(unique = true)
	public String nikname;
	
	public String pergunta;
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<Alternativa> alternativas;
	
	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", nikname=" + nikname + ", pergunta="
				+ pergunta + ", alternativas=" + alternativas + "]";
	}
}