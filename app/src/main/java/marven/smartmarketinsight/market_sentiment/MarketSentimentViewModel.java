package marven.smartmarketinsight.market_sentiment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.*;

public class MarketSentimentViewModel extends ViewModel {

    private final MutableLiveData<List<MarketSentiment>> marketSentimentsLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorStateLiveData = new MutableLiveData<>();

    public LiveData<List<MarketSentiment>> getMarketSentiments() {
        return marketSentimentsLiveData;
    }

    public LiveData<String> getErrorState() {
        return errorStateLiveData;
    }

    public void fetchMarketSentiment() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://tradestie.com/api/v1/apps/reddit";
        url="https://tradestie.com/api/v1/apps/reddit?date=2022-04-03";

        Log.i("MarketSentimentViewModel", "Fetching marketing sentiment from Reddit.");

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Dacă apare o eroare, setăm o listă goală sau afișăm un mesaj prin alte mijloace
                errorStateLiveData.postValue(e.getMessage()); // Postează mesajul de eroare
                marketSentimentsLiveData.postValue(new ArrayList<>()); // Postează o listă goală
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String responseBody = response.body().string();
                        JSONArray jsonArray = new JSONArray(responseBody);
                        List<MarketSentiment> sentiments = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            sentiments.add(new MarketSentiment(
                                    obj.getString("ticker"),
                                    obj.getString("sentiment"),
                                    obj.getDouble("no_of_comments"),
                                    obj.getString("sentiment_score")
                            ));
                        }

                        marketSentimentsLiveData.postValue(sentiments); // Postează lista de date
                        errorStateLiveData.postValue(null); // Șterge eroarea dacă cererea a avut succes
                    } catch (Exception e) {
                        errorStateLiveData.postValue("Error parsing data: " + e.getMessage());
                        marketSentimentsLiveData.postValue(new ArrayList<>());
                    }
                } else {
                    errorStateLiveData.postValue("Failed to fetch data. Response not successful.");
                    marketSentimentsLiveData.postValue(new ArrayList<>());
                }
            }
        });

    }
}