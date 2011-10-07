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
		} catch (CorruptIndexException e) {
			System.out.println("Problemas no arquivo de índice.");
		} catch (IOException e) {
			System.out.println("Erro ao tentar abrir índice.");
		}
	}
}
