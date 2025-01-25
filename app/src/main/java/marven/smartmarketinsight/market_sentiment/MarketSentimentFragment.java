package marven.smartmarketinsight.market_sentiment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import marven.smartmarketinsight.R;

public class MarketSentimentFragment extends Fragment {

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

}