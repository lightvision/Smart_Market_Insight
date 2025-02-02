package marven.smart_market_insight.ui.market_sentiment;

/**
 * Clasa pentru a reprezenta un Market Sentiment.
 */
public class MarketSentiment {
    private String ticker; // simbolul ticker
    private String sentiment; // sentimentul
    private double numberOfComments; // numarul de comentarii
    private String sentimentScore; // scorul sentimentului

    /**
     * Constructor pentru MarketSentiment.
     *
     * @param ticker simbolul ticker
     * @param sentiment sentimentul
     * @param numberOfComments numarul de comentarii
     * @param sentimentScore scorul sentimentului
     */
    public MarketSentiment(String ticker, String sentiment, double numberOfComments, String sentimentScore) {
        this.ticker = ticker;
        this.sentiment = sentiment;
        this.numberOfComments = numberOfComments;
        this.sentimentScore = sentimentScore;
    }

    /**
     * Returneaza simbolul ticker.
     *
     * @return simbolul ticker
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Returneaza sentimentul.
     *
     * @return sentimentul
     */
    public String getSentiment() {
        return sentiment;
    }

    /**
     * Returneaza numarul de comentarii.
     *
     * @return numarul de comentarii
     */
    public double getNumberOfComments() {
        return numberOfComments;
    }

    /**
     * Returneaza scorul sentimentului.
     *
     * @return scorul sentimentului
     */
    public String getSentimentScore() {
        return sentimentScore;
    }
}