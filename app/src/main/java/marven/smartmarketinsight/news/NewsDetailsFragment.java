package marven.smartmarketinsight.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import marven.smartmarketinsight.R;

public class NewsDetailsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_details, container, false);

        TextView titleView = view.findViewById(R.id.details_title);
        TextView descriptionView = view.findViewById(R.id.details_description);
        TextView sourceView = view.findViewById(R.id.details_source);

        if (getArguments() != null) {
            titleView.setText(getArguments().getString("title", "No Title"));
            descriptionView.setText(getArguments().getString("description", "No Description"));
            sourceView.setText(getArguments().getString("source", "Unknown Source"));
        }

        return view;
    }
}
