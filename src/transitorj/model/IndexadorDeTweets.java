package transitorj.model;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import twitter4j.Tweet;

public class IndexadorDeTweets {

	private File f;
	private FSDirectory dir;
	private Analyzer analyzer;

	public IndexadorDeTweets(String path) throws IOException {
		f = new File(path);

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

	public int indexa(List<Tweet> mensagens) throws CorruptIndexException,
			IOException {
		Document d;
		Field campoText;
		Field campoFromUser;
		Field campoDate;
		IndexWriter iw;
		IndexWriterConfig iwc;
		iwc = new IndexWriterConfig(Version.LUCENE_33, analyzer);
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		iw = new IndexWriter(dir, iwc);

		for (Tweet t : mensagens) {
			StringReader sr = new StringReader(t.getText());
			TokenStream ts = analyzer.tokenStream("text", sr);

			campoText = new Field("text", t.getText(), Field.Store.YES,
					Field.Index.ANALYZED);
			campoText.setTokenStream(ts);
			campoFromUser = new Field("fromUser", t.getFromUser(),
					Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS);
			campoDate = new Field("date", t.getCreatedAt().toString(),
					Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS);

			d = new Document();
			d.add(campoText);
			d.add(campoFromUser);
			d.add(campoDate);
			iw.addDocument(d);
		}

		iw.close();

		return mensagens.size();
	}
}
