package marven.smart_market_insight.ui.market_sentiment;

public class MarketSentiment {
    private String ticker;
    private String sentiment;
    private double numberOfComments;
    private String sentimentScore;

    public MarketSentiment(String ticker, String sentiment, double numberOfComments, String sentimentScore) {
        this.ticker = ticker;
        this.sentiment = sentiment;
        this.numberOfComments = numberOfComments;
        this.sentimentScore = sentimentScore;
    }

    public String getTicker() {
        return ticker;
    }

    public String getSentiment() {
        return sentiment;
    }

    public double getNumberOfComments() {
        return numberOfComments;
    }

    public String getSentimentScore() {
        return sentimentScore;
    }
}