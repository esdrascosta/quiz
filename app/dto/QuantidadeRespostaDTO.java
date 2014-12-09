package dto;

import java.io.Serializable;

public class QuantidadeRespostaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  String alternativa;
	public Integer quantidade;
	
	public QuantidadeRespostaDTO() {
	}
	
	public QuantidadeRespostaDTO(String alternativa, Integer quantidade) {
		this.alternativa = alternativa;
		this.quantidade = quantidade;
	}
	
}