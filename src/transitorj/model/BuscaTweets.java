package transitorj.model;

import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class BuscaTweets {

	private Twitter twitter;
	public static final int TPP = 20;
	public static final int RAIO = 30;
	public static final GeoLocation RJ = new GeoLocation(-22.899106, -43.208714);
	private static final String OPT = "trânsito OR tráfego OR engarrafamento OR "
			+ "lentidão OR fluxo OR obra OR interdita OR interditada OR "
			+ "interdição OR faixa OR colisão OR carro OR caminhão OR "
			+ "acidente OR ônibus";

	public BuscaTweets() {
		twitter = new TwitterFactory().getInstance();
	}

	public List<Tweet> buscaPagina(int pagina, int tpp) {
		if (tpp == 0)
			tpp = TPP;

		List<Tweet> resultados = null;
		String busca = OPT;

		Query query = new Query(busca);
		query.setRpp(tpp);
		query.setPage(pagina);
		query.setGeoCode(RJ, RAIO, Query.KILOMETERS);

		try {
			QueryResult queryResult = twitter.search(query);
			resultados = queryResult.getTweets();
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return resultados;
	}

	public List<Tweet> buscaTodos() {
		int tam = 0, pagina = 1;
		BuscaTweets buscaTweets = new BuscaTweets();

		List<Tweet> resultados = buscaTweets.buscaPagina(pagina, TPP);
		tam = resultados.size();

		while (tam == pagina * BuscaTweets.TPP) {
			++pagina;
			resultados.addAll(buscaTweets.buscaPagina(pagina, TPP));
			tam = resultados.size();
		}

		return resultados;
	}
}
