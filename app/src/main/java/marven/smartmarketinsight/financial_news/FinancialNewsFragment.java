package marven.smartmarketinsight.financial_news;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import marven.smartmarketinsight.R;

public class FinancialNewsFragment extends Fragment {

    private FinancialNewsViewModel mViewModel;

    public static FinancialNewsFragment newInstance() {
        return new FinancialNewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_financial_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FinancialNewsViewModel.class);
        // TODO: Use the ViewModel
    }

}