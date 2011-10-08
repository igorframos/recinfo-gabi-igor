package transitorj.controller;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Util {

	public static Date getDateFromTwitterString(String str) {
		DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss YYYY", Locale.US);
		
		Date data = null;
		try {
            data = df.parse(str);
            System.out.println(df.format(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return data;
	}
	
}
