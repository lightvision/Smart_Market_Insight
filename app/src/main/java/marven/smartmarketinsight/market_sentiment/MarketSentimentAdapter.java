package marven.smartmarketinsight.market_sentiment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import marven.smartmarketinsight.R;

public class MarketSentimentAdapter extends RecyclerView.Adapter<MarketSentimentAdapter.ViewHolder> {

    private final List<MarketSentiment> marketSentiments;

    public MarketSentimentAdapter(List<MarketSentiment> marketSentiments) {
        this.marketSentiments = marketSentiments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_market_sentiment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MarketSentiment sentiment = marketSentiments.get(position);
        holder.textTicker.setText(sentiment.getTicker());
        holder.textSentiment.setText("Sentiment: " + sentiment.getSentiment());
        holder.textComments.setText("Number of comments: " + sentiment.getNumberOfComments());
        holder.textSentimentScore.setText("Sentiment score: " + sentiment.getSentimentScore());
    }

    @Override
    public int getItemCount() {
        return marketSentiments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTicker, textSentiment, textComments, textSentimentScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTicker = itemView.findViewById(R.id.textTicker);
            textSentiment = itemView.findViewById(R.id.textSentiment);
            textComments = itemView.findViewById(R.id.textComments);
            textSentimentScore = itemView.findViewById(R.id.textSentimentScore);
        }
    }
}