package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import play.data.validation.ValidationError;

import com.avaje.ebean.Ebean;

@Entity
public class Pergunta{

	public Pergunta(){
		alternativas = new ArrayList<Alternativa>(4);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;

	@Column(unique = true)
	public String nikname;
	
	@NotBlank
	public String pergunta;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	public List<Alternativa> alternativas;
	
	public List<ValidationError> validate(){
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		Pergunta pergunta = Ebean.find(Pergunta.class).where().eq("nikname", this.nikname).findUnique();
		
		if(pergunta != null){
			errors.add (  new ValidationError ( "Apelido Usuario" , "O apelido para o osuario já existe"));
		}
		
		return errors.isEmpty () ? null : errors ;
	}
	
	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", nikname=" + nikname + ", pergunta="
				+ pergunta + ", alternativas=" + alternativas + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNikname() {
		return nikname;
	}

	public void setNikname(String nikname) {
		this.nikname = nikname;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}
}