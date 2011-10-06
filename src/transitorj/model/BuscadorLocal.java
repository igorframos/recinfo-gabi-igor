/**
 * 
 */
package transitorj.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

	public BuscadorLocal(String path) throws CorruptIndexException, IOException {
		f = new File(path);

		try {
			dir = FSDirectory.open(f);
		} catch (IOException e) {
			System.out.println("Caminho inválido.");
			throw e;
		}

		analyzer = new BrazilianAnalyzer(Version.LUCENE_33);
		
		is = new IndexSearcher(dir);
	}
	
	public ArrayList<LocalTweet> buscaRua (String rua) throws ParseException, IOException {
		ArrayList<LocalTweet> mensagens = new ArrayList<LocalTweet>();
		
		QueryParser qParser = new QueryParser(Version.LUCENE_33, "text", analyzer);
		rua = "\"" + rua + "\"";
		Query query = qParser.parse(rua);
		TopDocs results = is.search(query, 100, new Sort(SortField.FIELD_DOC));
		
		for (ScoreDoc scoreDoc : results.scoreDocs) {
			Document doc = is.doc(scoreDoc.doc);
			String text = doc.get("text");
			String fromUser = doc.get("fromUser");
			String date = doc.get("date");
			
			mensagens.add(new LocalTweet(text, fromUser, date));
		}
		
		return mensagens; 
	}
}
