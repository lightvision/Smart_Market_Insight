package marven.smart_market_insight.ui.market_sentiment;

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

import java.util.ArrayList;

import marven.smart_market_insight.databinding.FragmentMarketSentimentBinding;

/**
 * Fragment pentru afisarea Market Sentiments.
 */
public class MarketSentimentFragment extends Fragment {

    private static final String TAG = "MarketSentimentFragment";

    private MarketSentimentViewModel viewModel; // ViewModel pentru Market Sentiment
    private FragmentMarketSentimentBinding binding; // Binding pentru fragment
    private MarketSentimentAdapter adapter; // Adapter pentru RecyclerView

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMarketSentimentBinding.inflate(inflater, container, false);
        setupViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();
        setupSwipeRefreshLayout();

        Log.i(TAG, "Starting initial data fetch.");
        binding.swipeRefreshLayout.setRefreshing(true);
        viewModel.fetchMarketSentiment();
    }

    /**
     * Configureaza RecyclerView.
     */
    private void setupRecyclerView() {
        binding.recyclerViewMarketSentiment.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new MarketSentimentAdapter(new ArrayList<>());
        binding.recyclerViewMarketSentiment.setAdapter(adapter);
    }

    /**
     * Configureaza ViewModel-ul.
     */
    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MarketSentimentViewModel.class);

        // Observam lista de Market Sentiments
        viewModel.getMarketSentiments().observe(getViewLifecycleOwner(), sentiments -> {
            // Logam numarul de elemente actualizate
            Log.i(TAG, "Data updated successfully: " + sentiments.size() + " items.");
            // Actualizam datele din adapter
            adapter.updateData(sentiments);
            // Oprim animatia de refresh
            binding.swipeRefreshLayout.setRefreshing(false);
        });

        // Observam starea de eroare
        viewModel.getErrorState().observe(getViewLifecycleOwner(), error -> {
            if (error != null && !error.isEmpty()) {
                // Logam eroarea
                Log.e(TAG, "Error during data fetch: " + error);
                // Afisam un mesaj de eroare
                Toast.makeText(requireContext(), "Error: " + error, Toast.LENGTH_SHORT).show();
                // Oprim animatia de refresh
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * Configureaza SwipeRefreshLayout.
     */
    private void setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            Log.i(TAG, "Swipe-to-Refresh started.");
            viewModel.fetchMarketSentiment();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}