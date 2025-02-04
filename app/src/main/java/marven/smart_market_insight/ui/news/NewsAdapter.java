package marven.smart_market_insight.ui.news;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import marven.smart_market_insight.GlideApp;
import marven.smart_market_insight.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsItem> newsList;

    public NewsAdapter(List<NewsItem> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsItem newsItem = newsList.get(position);
        holder.title.setText(newsItem.getTitle());
        holder.description.setText(newsItem.getDescription());
        holder.pubDate.setText(newsItem.getPubDate()); // Bind publication date

        GlideApp.with(holder.itemView.getContext())
                .load(newsItem.getImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .listener(new com.bumptech.glide.request.RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        // Hide the ImageView if the image loading fails
                        holder.image.setVisibility(View.GONE);
                        return false; // Allow Glide to handle the error placeholder
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        // Show the ImageView if the image loads successfully
                        holder.image.setVisibility(View.VISIBLE);
                        return false; // Allow Glide to handle the resource
                    }
                })
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void updateNewsList(List<NewsItem> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView pubDate; // New TextView for publication date
        ImageView image;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            description = itemView.findViewById(R.id.news_description);
            pubDate = itemView.findViewById(R.id.news_pub_date); // Initialize publication date TextView
            image = itemView.findViewById(R.id.news_image);
        }
    }
}