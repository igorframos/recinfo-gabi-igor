package transitorj.controller;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;

import transitorj.model.BuscadorLocal;
import transitorj.model.IndexadorDeTweets;
import transitorj.model.LocalTweet;
import twitter4j.Tweet;

public class ControleBusca {

	private BuscadorLocal buscadorLocal = null;

	public ControleBusca() {
		try {
			/*
			 * indexador = new IndexadorDeTweets(
			 * "C:/Users/Gabriel/Desktop/Gabriel/Faculdade/RecInfo/Workspace/recinfo-gabi-igor"
			 * ); System.out.println("Criado índice.");
			 * 
			 * buscadorLocal = new BuscadorLocal(
			 * "C:/Users/Gabriel/Desktop/Gabriel/Faculdade/RecInfo/Workspace/recinfo-gabi-igor"
			 * ); System.out.println("Criado buscador.");
			 */

			buscadorLocal = new BuscadorLocal(
					"/home/igorfr/workspace/twitterindex");
			System.out.println("Criado buscador.");

		} catch (CorruptIndexException e) {
			System.out.println("Problemas no arquivo de índice.");
		} catch (IOException e) {
			System.out.println("Erro ao tentar abrir índice.");
		}
	}

	public List<Tweet> trataEntrada(String nomeRua) {
		return null;
	}

	public String[] buscaEntrada(String nomeRua) {
		String[] vetor = null;
		List<LocalTweet> lista;
		try {
			lista = buscadorLocal.buscaRua(nomeRua);
			int tam = lista.size();
			vetor = new String[tam + 1];
			for (int i = 0; i < tam; i++) {
				vetor[i] = i + 1 + " - " + lista.get(i).getDate() + " - "
						+ lista.get(i).getText();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return vetor;
	}
}
