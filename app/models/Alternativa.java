package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Alternativa{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
	@NotBlank
	public String descricao;
	
	public Boolean alternativaCerta;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAlternativaCerta() {
		return alternativaCerta;
	}

	public void setAlternativaCerta(Boolean alternativaCerta) {
		this.alternativaCerta = alternativaCerta;
	}
}