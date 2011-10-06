package transitorj.teste;

import transitorj.model.BuscaTweets;
import transitorj.model.BuscaTweetsFactory;
import transitorj.model.BuscadorLocal;
import transitorj.model.IndexadorDeTweets;

public class TesteModel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuscaTweets buscaTweets = new BuscaTweetsFactory().getInstance();
		IndexadorDeTweets indexador = new IndexadorDeTweets("/home/igorfr/workspace/twitterindex");
		BuscadorLocal buscadorLocal = new BuscadorLocal("/home/igorfr/workspace/twitterindex");
		
		System.out.println(buscaTweets.buscaPagina("borges de medeiros", 1).size()
				+ " <= " + BuscaTweets.TPP);
		System.out.println(buscaTweets.buscaTodos("borges de medeiros").size());
	}
}
