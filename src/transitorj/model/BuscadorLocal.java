/**
 * 
 */
package transitorj.model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeSet;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class BuscadorLocal {

	private File f;
	private FSDirectory dir;
	private Analyzer analyzer;
	private IndexSearcher is;
	private HashMap<String,String> mapa;

	public BuscadorLocal(String path) throws CorruptIndexException, IOException {
		f = new File(path);
		criaMapa();

		try {
			dir = FSDirectory.open(f);
		} catch (IOException e) {
			System.out.println("Caminho inválido.");
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat(
					"dd/mm/yyyy HH:mm:ss zzz");
			System.out.println(df.format(c.getTime()));
			throw e;
		}

		analyzer = new BrazilianAnalyzer(Version.LUCENE_33);
	}

	public ArrayList<LocalTweet> buscaRua(String rua) throws ParseException,
			IOException {

		is = new IndexSearcher(dir);
		ArrayList<LocalTweet> mensagens = new ArrayList<LocalTweet>();
		TreeSet<LocalTweet> mensagensSet = new TreeSet<LocalTweet>();

		QueryParser qParser = new QueryParser(Version.LUCENE_33, "text",
				analyzer);
		rua = ajuste(rua);
		Query query = qParser.parse(rua);
		TopDocs results = is.search(query, is.maxDoc(), new Sort(
				SortField.FIELD_DOC));

		for (ScoreDoc scoreDoc : results.scoreDocs) {
			Document doc = is.doc(scoreDoc.doc);
			String text = doc.get("text");
			String fromUser = doc.get("fromUser");
			String date = doc.get("date");

			mensagensSet.add(new LocalTweet(text, fromUser, date));
		}

		is.close();

		mensagens.addAll(mensagensSet);

		return mensagens;
	}
	
	String ajuste(String rua) {
		rua = rua.toLowerCase();
		System.out.println(rua);
		if (mapa.containsKey(rua)) {
			return mapa.get(rua);
		}
		
		String ans = "\"" + rua + "\"";
		
		if (rua.contains(" ")) {
			String[] v = rua.split(" ");
			ans += " OR ";
			for (String s : v) {
				ans += s;
			}
		}
		
		return ans;
	}
	
	void criaMapa() {
		mapa = new HashMap<String, String>();
		
		mapa.put("vermelha", "vermelha OR linhavermelha OR LV");
		mapa.put("linha vermelha", "vermelha OR linhavermelha OR LV");
		mapa.put("amarela", "amarela OR linhaamarela OR LA");
		mapa.put("linha amarela", "amarela OR linhaamarela OR LA");
		mapa.put("brasil", "brasil OR br OR avbrasil OR avenidabrasil");
		mapa.put("copacabana", "copacabana OR copa");
		mapa.put("américas", "américas OR avamericas");
		mapa.put("botafogo", "botafogo OR btfg");
		mapa.put("ayrton sena","\"ayrton senna\" OR \"ayrton sena\" OR ayrtonsenna OR ayrtonsena");
		mapa.put("ayrton senna","\"ayrton senna\" OR \"ayrton sena\" OR ayrtonsenna OR ayrtonsena");
		mapa.put("ponte", "ponte OR \"ponte rio niterói\" OR \"sentido rio\" OR \"sentido niterói\" OR rioniteroi");
		mapa.put("rio-niterói", "ponte OR \"ponte rio niterói\" OR \"sentido rio\" OR \"sentido niterói\" OR rioniteroi");
		mapa.put("rio niterói", "ponte OR \"ponte rio niterói\" OR \"sentido rio\" OR \"sentido niterói\" OR rioniteroi");
		mapa.put("rio-niteroi", "ponte OR \"ponte rio niterói\" OR \"sentido rio\" OR \"sentido niterói\" OR rioniteroi");
		mapa.put("rio niteroi", "ponte OR \"ponte rio niterói\" OR \"sentido rio\" OR \"sentido niterói\" OR rioniteroi");
		mapa.put("tijuca", "tijuca -barra");
	}
}
