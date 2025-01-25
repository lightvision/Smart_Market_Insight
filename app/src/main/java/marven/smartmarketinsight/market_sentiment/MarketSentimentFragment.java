package marven.smartmarketinsight.market_sentiment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import marven.smartmarketinsight.R;

public class MarketSentimentFragment extends Fragment {



    private TextView sentimentTextView;
    private MarketSentimentViewModel viewModel;

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
        viewModel = new ViewModelProvider(this).get(MarketSentimentViewModel.class);
        // TODO: Use the ViewModel
    }





    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sentimentTextView = view.findViewById(R.id.textMarketSentiment);

        // Inițializează ViewModel
        viewModel = new ViewModelProvider(this).get(MarketSentimentViewModel.class);

        // Observă LiveData pentru actualizări
        viewModel.getMarketSentiment().observe(getViewLifecycleOwner(), sentimentTextView::setText);

        // Începe preluarea datelor
        viewModel.fetchMarketSentiment();
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        TextView sentimentTextView = view.findViewById(R.id.textMarketSentiment);
//
//        // Simulează afișarea datelor fake
//        fetchMarketSentimentRaw();
////        sentimentTextView.setText(fakeData);
//    }


}