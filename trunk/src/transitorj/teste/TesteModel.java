package transitorj.teste;

import transitorj.model.BuscaTweets;
import transitorj.model.BuscaTweetsFactory;

public class TesteModel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuscaTweets buscaTweets = new BuscaTweetsFactory().getInstance();
		
		System.out.println(buscaTweets.buscaPagina("borges de medeiros", 1).size()
				+ " <= " + BuscaTweets.TPP);
		System.out.println(buscaTweets.buscaTodos("borges de medeiros").size());
	}
}
