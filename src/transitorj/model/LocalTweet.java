package transitorj.model;

public class LocalTweet {
	private String text;
	private String fromUser;
	private String date;

	public LocalTweet(String text, String fromUser, String date) {
		this.text = text;
		this.fromUser = fromUser;
		this.date = date;
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
}
