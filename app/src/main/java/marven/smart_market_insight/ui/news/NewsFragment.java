package marven.smart_market_insight.ui.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import marven.smart_market_insight.databinding.FragmentNewsBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsAdapter newsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        newsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        RecyclerView recyclerView = binding.newsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        newsAdapter = new NewsAdapter(new ArrayList<>());
        recyclerView.setAdapter(newsAdapter);

        binding.swipeRefresh.setOnRefreshListener(this::fetchNews);

        fetchNews();

        return root;
    }

    private void fetchNews() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://feeds.bbci.co.uk/news/business/rss.xml";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(() -> binding.swipeRefresh.setRefreshing(false));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();

                    List<NewsItem> newsItems = parseRSSFeed(responseBody);
                    getActivity().runOnUiThread(() -> {
                        newsAdapter.updateNewsList(newsItems);
                        binding.swipeRefresh.setRefreshing(false);
                    });
                } else {
                    getActivity().runOnUiThread(() -> binding.swipeRefresh.setRefreshing(false));
                }
            }
        });
    }

    private List<NewsItem> parseRSSFeed(String rssFeed) {
        List<NewsItem> newsItems = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(rssFeed, "", org.jsoup.parser.Parser.xmlParser());
            Elements items = doc.select("item");
            for (Element item : items) {
                String title = item.select("title").text();
                String description = item.select("description").text();
                String imageUrl = item.select("media\\:thumbnail").attr("url");
                String pubDate = item.select("pubDate").text();
                String source = "BBC News"; // Set source

                // Log the extracted image URL and publication date
                Log.d("NewsFragment", "Extracted image URL: " + imageUrl);
                Log.d("NewsFragment", "Extracted publication date: " + pubDate);

                newsItems.add(new NewsItem(title, description, imageUrl, pubDate, source));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsItems;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}