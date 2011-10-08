package transitorj.model;

import java.io.IOException;
import java.util.List;

import twitter4j.Tweet;

public class Crawler extends Thread {

	private static final int SLEEP_TIME = 60000;
	private BuscaTweets buscaTweets = new BuscaTweets();
	private IndexadorDeTweets indexador = null;
	private List<Tweet> mensagens = null;
	private int indexados;
	Tweet last;

	public Crawler() {
		last = null;

		try {
			/*
			 * indexador = new IndexadorDeTweets(
			 * "C:/Users/Gabriel/Desktop/Gabriel/Faculdade/RecInfo/Workspace/recinfo-gabi-igor"
			 * ); System.out.println("Criado índice.");
			 */

			indexador = new IndexadorDeTweets(
					"/home/igorfr/workspace/twitterindex");
			System.out.println("Criado índice.");

		} catch (IOException e) {
			System.out.println("Erro ao criar índice.");
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				while (true) {
					mensagens = buscaTweets.buscaTodos(last);
					System.out.println("Tweets buscados.");

					if (mensagens.size() > 0)
						last = mensagens.get(mensagens.size() - 1);

					for (Tweet t : mensagens) {
						System.out.println(t.getCreatedAt());
						System.out.println(t.getFromUser() + ": " + t.getText()
								+ "\n");
					}

					indexados = indexador.indexa(mensagens);
					System.out.println("Tweets indexados: " + indexados);

					Thread.sleep(SLEEP_TIME);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
