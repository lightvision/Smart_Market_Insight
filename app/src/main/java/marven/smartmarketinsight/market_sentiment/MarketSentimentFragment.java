package marven.smartmarketinsight.market_sentiment;

import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import marven.smartmarketinsight.R;

public class MarketSentimentFragment extends Fragment {

    // text adaugat pentru test
    private TextView tvMarketSentiment;
    // sfatsit de cod adaugat pentru test

    private MarketSentimentViewModel mViewModel;

    public static MarketSentimentFragment newInstance() {
        return new MarketSentimentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_market_sentiment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MarketSentimentViewModel.class);
        // TODO: Use the ViewModel
    }

    // cod adaugat pentru test
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvMarketSentiment = view.findViewById(R.id.textMarketSentiment);

        // Fetch JSON data (hard-coded for testing)
        fetchMarketSentiment();
    }

    private void fetchMarketSentiment() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("https://tradestie.com/api/v1/apps/reddit?date=2022-04-03");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    reader.close();
                    return result.toString();
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                tvMarketSentiment.setText(result); // Display JSON in the TextView
            }
        }.execute();
    }
}