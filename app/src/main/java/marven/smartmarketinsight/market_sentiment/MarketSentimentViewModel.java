package marven.smartmarketinsight.market_sentiment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.*;

import java.io.IOException;

import okhttp3.*;

public class MarketSentimentViewModel extends ViewModel {

    private final MutableLiveData<String> marketSentimentLiveData = new MutableLiveData<>();

    public LiveData<String> getMarketSentiment() {
        return marketSentimentLiveData;
    }

    /**
     *
     */
    public void fetchMarketSentiment() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://tradestie.com/api/v1/apps/reddit";
        // TODO de comentat randul de mai jos
        url="https://tradestie.com/api/v1/apps/reddit?date=2022-04-03";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                marketSentimentLiveData.postValue("Error: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();

                    try {
                        // Parse JSON and format data
                        JSONArray jsonArray = new JSONArray(responseBody);
                        if (jsonArray.length() == 0) {
                            // Caz: lista este goală
                            marketSentimentLiveData.postValue("No data available for today.");
                            return;
                        }

                        StringBuilder formattedData = new StringBuilder();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            formattedData.append("Ticker: ").append(obj.getString("ticker"))
                                    .append("\nSentiment: ").append(obj.getString("sentiment"))
                                    .append("\nNumber of comments: ").append(obj.getDouble("no_of_comments"))
                                    .append("\nSentiment score: ").append(obj.getString("sentiment_score"))
                                    .append("\n\n");
                        }

                        marketSentimentLiveData.postValue(formattedData.toString());
                    } catch (Exception e) {
                        marketSentimentLiveData.postValue("Error parsing JSON: " + e.getMessage());
                    }
                } else {
                    marketSentimentLiveData.postValue("Failed to fetch data.");
                }
            }
        });
    }
}