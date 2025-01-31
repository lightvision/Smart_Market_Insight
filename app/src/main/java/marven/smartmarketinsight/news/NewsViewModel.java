package marven.smartmarketinsight.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {
    private final MutableLiveData<List<NewsModel>> newsLiveData = new MutableLiveData<>();

    public NewsViewModel() {
        loadMockNews();
    }

    public LiveData<List<NewsModel>> getNews() {
        return newsLiveData;
    }

    public void refreshNews() {
        loadMockNews(); // Reîncarcă datele de test
    }


    private void loadMockNews() {
        List<NewsModel> mockNews = new ArrayList<>();

        // Știre cu detalii (are URL valid)
        mockNews.add(new NewsModel(
                "Breaking News with Details",
                "This is a test description with full details.",
                "https://example.com/full-article",
                "https://example.com/image.jpg",
                "Just now",
                "Mock Source"
        ));

        // Știre FĂRĂ detalii (nu are URL)
        mockNews.add(new NewsModel(
                "News Without Full Details",
                "This is a short description. No full article available.",
                "",
                "https://example.com/image.jpg",
                "10 minutes ago",
                "Another Source"
        ));

        // Altă știre cu detalii
        mockNews.add(new NewsModel(
                "Second Full Article",
                "Another test description.",
                "https://example.com/second-article",
                "",
                "5 minutes ago",
                "Test Provider"
        ));

        newsLiveData.setValue(mockNews);
    }


}
