package transitorj.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;

import twitter4j.Tweet;
import transitorj.model.BuscaTweets;
import transitorj.model.BuscadorLocal;
import transitorj.model.IndexadorDeTweets;
import transitorj.model.LocalTweet;

public class ControleBusca {
	
	private BuscaTweets buscaTweets = new BuscaTweets();
	private IndexadorDeTweets indexador = null;
	private BuscadorLocal buscadorLocal = null;
	private List<Tweet> mensagens = null;
	private int indexados;
	
	public ControleBusca() {
		try {
			indexador = new IndexadorDeTweets(
					"C:/Users/Gabriel/Desktop/Gabriel/Faculdade/RecInfo/Workspace/recinfo-gabi-igor");
			System.out.println("Criado índice.");

			buscadorLocal = new BuscadorLocal(
					"C:/Users/Gabriel/Desktop/Gabriel/Faculdade/RecInfo/Workspace/recinfo-gabi-igor");
			System.out.println("Criado buscador.");

			mensagens = buscaTweets.buscaTodos();
			System.out.println("Tweets buscados.");

			indexados = indexador.indexa(mensagens);
			System.out.println("Tweets indexados: " + indexados);

			/*
			ArrayList<LocalTweet> resultados = buscadorLocal
					.buscaRua("linha vermelha");
			System.out.println("Resultados encontrados: " + resultados.size());

			for (LocalTweet t : resultados) {
				System.out.println(t.getFromUser() + "\t" + t.getDate());
				System.out.println(t.getText() + "\n");
			}
			*/

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
		String[] vetor = new String[30];
		
		List<LocalTweet> lista;
		try {
			lista = buscadorLocal.buscaRua(nomeRua);
			for(int i = 0; i < lista.size(); i++) {
				vetor[i] = i+1 + " - " + lista.get(i).getDate() + " - " + lista.get(i).getText();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return vetor;
	}
}
