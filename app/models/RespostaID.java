package models;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class RespostaID implements Serializable{
	
	/** serial version */
	private static final long serialVersionUID = -4983892268193123369L;
	
	public long perguntaId;
	
	public long alternativaId;

	public String nicknameUser;
	

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(perguntaId).append(alternativaId).append(nicknameUser).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}