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
			+ "interdição OR faixa ";

	private BuscaTweets() {
		twitter = new TwitterFactory().getInstance();
	}

	public static BuscaTweets newInstance() {
		return new BuscaTweets();
	}

	public List<Tweet> buscaPagina(String rua, int pagina) {
		List<Tweet> resultados = null;
		rua = "\"" + rua + "\"";
		String busca = OPT + rua;

		Query query = new Query(busca);
		query.setRpp(TPP);
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

	public List<Tweet> buscaTodos(String rua) {
		int tam = 0, pagina = 1;
		BuscaTweets buscaTweets = new BuscaTweetsFactory().getInstance();

		List<Tweet> resultados = buscaTweets.buscaPagina(rua, pagina);
		tam = resultados.size();

		while (tam == pagina * BuscaTweets.TPP) {
			++pagina;
			resultados.addAll(buscaTweets.buscaPagina(rua, pagina));
			tam = resultados.size();
		}

		return resultados;
	}
}
