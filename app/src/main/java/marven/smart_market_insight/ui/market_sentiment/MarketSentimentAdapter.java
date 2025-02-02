package marven.smart_market_insight.ui.market_sentiment;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import marven.smart_market_insight.R;

/**
 * Adapter pentru RecyclerView care afiseaza lista de Market Sentiments.
 */
public class MarketSentimentAdapter extends RecyclerView.Adapter<MarketSentimentAdapter.ViewHolder> {

    // Lista de Market Sentiments
    private List<MarketSentiment> marketSentiments;

    /**
     * Constructor pentru MarketSentimentAdapter.
     *
     * @param marketSentiments lista de Market Sentiments
     */
    public MarketSentimentAdapter(List<MarketSentiment> marketSentiments) {
        this.marketSentiments = marketSentiments;
    }

    // Metoda pentru actualizarea datelor
    public void updateData(List<MarketSentiment> newMarketSentiments) {
        this.marketSentiments = newMarketSentiments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infleaza layout-ul pentru fiecare element din lista
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_market_sentiment, parent, false);
        return new ViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        // Leaga datele de Market Sentiment la ViewHolder
//        MarketSentiment sentiment = marketSentiments.get(position);
//        holder.textTicker.setText(sentiment.getTicker());
//        holder.textSentiment.setText("Sentiment: " + sentiment.getSentiment());
//        holder.textComments.setText("Number of comments: " + sentiment.getNumberOfComments());
//        holder.textSentimentScore.setText("Sentiment score: " + sentiment.getSentimentScore());
//    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MarketSentiment sentiment = marketSentiments.get(position);
        holder.textTicker.setText(sentiment.getTicker());

        // Create a SpannableString for the sentiment text
        String sentimentText = "Sentiment: " + sentiment.getSentiment();
        SpannableString spannableString = new SpannableString(sentimentText);
        int start = sentimentText.indexOf(sentiment.getSentiment());
        int end = start + sentiment.getSentiment().length();
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.textSentiment.setText(spannableString);
        holder.textComments.setText("Number of comments: " + sentiment.getNumberOfComments());
        holder.textSentimentScore.setText("Sentiment score: " + sentiment.getSentimentScore());
    }

    @Override
    public int getItemCount() {
        // Returneaza numarul de elemente din lista
        return marketSentiments.size();
    }

    /**
     * ViewHolder pentru Market Sentiment.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTicker, textSentiment, textComments, textSentimentScore;

        /**
         * Constructor pentru ViewHolder.
         *
         * @param itemView view-ul pentru fiecare element din lista
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTicker = itemView.findViewById(R.id.textTicker);
            textSentiment = itemView.findViewById(R.id.textSentiment);
            textComments = itemView.findViewById(R.id.textComments);
            textSentimentScore = itemView.findViewById(R.id.textSentimentScore);
        }
    }
}