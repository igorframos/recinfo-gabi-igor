package transitorj.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;

import transitorj.model.BuscadorLocal;
import transitorj.model.LocalTweet;
import twitter4j.Tweet;

public class ControleBusca {

	private BuscadorLocal buscadorLocal = null;

	public ControleBusca() {
		try {
			buscadorLocal = new BuscadorLocal(
					".");
			System.out.println("Criado buscador.");

		} catch (CorruptIndexException e) {
			System.out.println("Problemas no arquivo de índice.");
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat(
					"dd/mm/yyyy HH:mm:ss zzz");
			System.out.println(df.format(c.getTime()));
		} catch (IOException e) {
			System.out.println("Erro ao tentar abrir índice.");
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat(
					"dd/mm/yyyy HH:mm:ss zzz");
			System.out.println(df.format(c.getTime()));
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
			vetor = new String[2*tam + 4];
			for (int i = 0; i < tam; i++) {
				vetor[2*i] = i + 1 + " - " + lista.get(i).getDate() + " - " + lista.get(i).getFromUser();
				vetor[2*i+1] = "\t" + lista.get(i).getText();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return vetor;
	}
}
