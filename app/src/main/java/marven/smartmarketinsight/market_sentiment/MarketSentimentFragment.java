package marven.smartmarketinsight.market_sentiment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import marven.smartmarketinsight.R;

public class MarketSentimentFragment extends Fragment {

    private static final String TAG = "MarketSentimentFragment";

    private MarketSentimentViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_market_sentiment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMarketSentiment);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        // Inițializează ViewModel
        viewModel = new ViewModelProvider(this).get(MarketSentimentViewModel.class);

        // Observă datele din ViewModel
        viewModel.getMarketSentiments().observe(getViewLifecycleOwner(), sentiments -> {
            Log.i(TAG, "Data updated successfully: " + sentiments.size() + " items.");
            MarketSentimentAdapter adapter = new MarketSentimentAdapter(sentiments);
            recyclerView.setAdapter(adapter);
            swipeRefreshLayout.setRefreshing(false); // Oprește animația de refresh
        });

        // Observă eventualele erori
        viewModel.getErrorState().observe(getViewLifecycleOwner(), error -> {
            if (error != null && !error.isEmpty()) {
                Log.e(TAG, "Error during data fetch: " + error);
                Toast.makeText(requireContext(), "Error: " + error, Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false); // Oprește animația de refresh
            }
        });

        // Configurare Swipe-to-Refresh
        swipeRefreshLayout.setOnRefreshListener(() -> {
            Log.i(TAG, "Swipe-to-Refresh started.");
            viewModel.fetchMarketSentiment();
        });

        // Pornește preluarea inițială a datelor
        Log.i(TAG, "Starting initial data fetch.");
        swipeRefreshLayout.setRefreshing(true);
        viewModel.fetchMarketSentiment();
    }
}
