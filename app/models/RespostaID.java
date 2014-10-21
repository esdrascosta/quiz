package models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RespostaID implements Serializable{
	
	/** serial version */
	private static final long serialVersionUID = -4983892268193123369L;
	
	public long perguntaId;
	
	public long alternativaId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (alternativaId ^ (alternativaId >>> 32));
		result = prime * result + (int) (perguntaId ^ (perguntaId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespostaID other = (RespostaID) obj;
		if (alternativaId != other.alternativaId)
			return false;
		if (perguntaId != other.perguntaId)
			return false;
		return true;
	}
	
}