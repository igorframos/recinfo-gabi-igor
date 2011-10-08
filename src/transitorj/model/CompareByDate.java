package transitorj.model;

import java.util.Comparator;

import transitorj.controller.Util;

public class CompareByDate implements Comparator<LocalTweet> {

	@Override
	public int compare(LocalTweet arg0, LocalTweet arg1) {
		java.util.Date d0 = Util.getDateFromTwitterString(arg0.getDate());
		java.util.Date d1 = Util.getDateFromTwitterString(arg1.getDate());
		
		return - d0.compareTo(d1);
	}

}
