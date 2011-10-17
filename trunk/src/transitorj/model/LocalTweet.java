package transitorj.model;

public class LocalTweet implements Comparable<LocalTweet> {
	private String text;
	private String fromUser;
	private String date;

	public LocalTweet(String text, String fromUser, String date) {
		this.text = text;
		this.fromUser = fromUser;
		this.date = date;
	}

	public boolean equals(LocalTweet t) {
		if (this.text.equalsIgnoreCase(t.getText())
				&& this.fromUser.equalsIgnoreCase(t.getFromUser())
				&& this.date.equalsIgnoreCase(t.getDate())) {
			return true;
		}
		return false;
	}

	public String getText() {
		return text;
	}

	public String getFromUser() {
		return fromUser;
	}

	public String getDate() {
		return date;
	}

	@Override
	public int compareTo(LocalTweet t) {
		if (!this.date.equalsIgnoreCase(t.getDate()))
			return -this.date.compareToIgnoreCase(t.getDate());

		if (!this.fromUser.equalsIgnoreCase(t.getFromUser()))
			return this.fromUser.compareToIgnoreCase(t.getFromUser());
		
		if (!this.text.equalsIgnoreCase(t.getText()))
			return this.text.compareToIgnoreCase(t.getText());
		
		return 0;
	}
}
