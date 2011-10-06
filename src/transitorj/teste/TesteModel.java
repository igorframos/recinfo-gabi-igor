package transitorj.teste;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;

import transitorj.model.BuscaTweets;
import transitorj.model.BuscadorLocal;
import transitorj.model.IndexadorDeTweets;
import transitorj.model.LocalTweet;
import twitter4j.Tweet;

public class TesteModel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuscaTweets buscaTweets = new BuscaTweets();

		try {
			IndexadorDeTweets indexador = new IndexadorDeTweets(
					"/home/igorfr/workspace/twitterindex");
			System.out.println("Criado índice.");
			
			BuscadorLocal buscadorLocal = new BuscadorLocal(
					"/home/igorfr/workspace/twitterindex");
			System.out.println("Criado buscador.");
			
			List<Tweet> mensagens = buscaTweets.buscaTodos();
			System.out.println("Tweets buscados.");
			
			int indexados = indexador.indexa(mensagens);
			System.out.println("Tweets indexados: " + indexados);
			
			ArrayList<LocalTweet> resultados = buscadorLocal.buscaRua("linha vermelha");
			System.out.println("Resultados encontrados: " + resultados.size());
			
			for (LocalTweet t : resultados) {
				System.out.println(t.getFromUser() + "\t" + t.getDate());
				System.out.println(t.getText() + "\n");
			}
			
		} catch (CorruptIndexException e) {
			System.out.println("Problemas no arquivo de índice.");
		} catch (IOException e) {
			System.out.println("Erro ao tentar abrir índice.");
		} catch (ParseException e) {
			System.out.println("Não foi possível compreender a busca.");
			e.printStackTrace();
		}
	}
}
