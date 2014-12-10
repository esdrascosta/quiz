package manager;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;

import dto.QuantidadeRespostaDTO;

public class ResultadoDAO {

	public static List<QuantidadeRespostaDTO> respostas(String perguntaNikName){
		 
		StringBuilder query = new StringBuilder();
		
		query.append("select alt.descricao as descricao_alternativa, count(alternativa_id) as quantidade_resposta " );
		query.append("from resposta resp inner join alternativa alt on ");
		query.append("						resp.alternativa_id = alt.id ");
		query.append("					  inner join pergunta perg on ");
		query.append("						resp.pergunta_id = perg.id ");
		query.append("where perg.nikname = :nickname group by descricao_alternativa");
		
		SqlQuery sqlQuery = Ebean.createSqlQuery( query.toString());
		sqlQuery.setParameter("nickname", perguntaNikName);

		List<SqlRow> list = sqlQuery.findList();
		
		List<QuantidadeRespostaDTO> action = new ArrayList<QuantidadeRespostaDTO>();
		
		
		list.forEach((e)-> {
			action.add(new QuantidadeRespostaDTO(e.getString("descricao_alternativa"), e.getInteger("quantidade_resposta")));
		});
		
		return action;
	}
}