package transitorj.model;

import java.util.Comparator;

import twitter4j.Tweet;

public class CompareByDate implements Comparator<Tweet> {

	@Override
	public int compare(Tweet arg0, Tweet arg1) {
		return arg0.getCreatedAt().compareTo(arg1.getCreatedAt());
	}

}
