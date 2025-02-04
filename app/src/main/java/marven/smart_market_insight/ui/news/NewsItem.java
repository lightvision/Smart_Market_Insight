package marven.smart_market_insight.ui.news;

public class NewsItem {
    private String title;
    private String description;
    private String imageUrl;
    private String pubDate;
    private String source; // New field for source

    public NewsItem(String title, String description, String imageUrl, String pubDate, String source) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.pubDate = pubDate;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getSource() {
        return source;
    }
}