package marven.smart_market_insight.ui.news;

    public class NewsItem {
        private String title;
        private String description;
        private String imageUrl;
        private String pubDate; // New field for publication date

        public NewsItem(String title, String description, String imageUrl, String pubDate) {
            this.title = title;
            this.description = description;
            this.imageUrl = imageUrl;
            this.pubDate = pubDate;
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
    }