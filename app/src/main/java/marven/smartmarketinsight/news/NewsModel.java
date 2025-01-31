package marven.smartmarketinsight.news;

public class NewsModel {
    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private String pubDate;
    private String source;

    public NewsModel(String title, String description, String url, String imageUrl, String pubDate, String source) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.pubDate = pubDate;
        this.source = source;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getUrl() { return url; }
    public String getImageUrl() { return imageUrl; }
    public String getPubDate() { return pubDate; }
    public String getSource() { return source; }
}
