package transitorj.teste;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteModel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
		System.out.println(df);

		try {
			Date today = df.parse("Fri Oct 07 19:30:43 BRT 2011");
			System.out.println("Today = " + df.format(today));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
