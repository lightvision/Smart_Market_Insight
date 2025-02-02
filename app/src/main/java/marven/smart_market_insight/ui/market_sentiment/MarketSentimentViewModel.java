package marven.smart_market_insight.ui.market_sentiment;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MarketSentimentViewModel extends AndroidViewModel {

    private final MutableLiveData<List<MarketSentiment>> marketSentimentsLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorStateLiveData = new MutableLiveData<>();

    public MarketSentimentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<MarketSentiment>> getMarketSentiments() {
        return marketSentimentsLiveData;
    }

    public LiveData<String> getErrorState() {
        return errorStateLiveData;
    }

    public void fetchMarketSentiment() {
        if (!isNetworkAvailable()) {
            errorStateLiveData.postValue("No network connection available.");
            marketSentimentsLiveData.postValue(new ArrayList<>());
            return;
        }

        OkHttpClient client = new OkHttpClient();
        String url = "https://tradestie.com/api/v1/apps/reddit";

        Log.i("MarketSentimentVM", "Fetching marketing sentiment from Reddit.");

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                errorStateLiveData.postValue(e.getMessage());
                marketSentimentsLiveData.postValue(new ArrayList<>());
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

                        marketSentimentsLiveData.postValue(sentiments);
                        errorStateLiveData.postValue(null);
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}