package transitorj.model;

public class BuscaTweetsFactory {

	private BuscaTweets instance = null;

	public BuscaTweets getInstance() {
		if (instance == null) {
			instance = BuscaTweets.newInstance();
		}

		return instance;
	}

}
