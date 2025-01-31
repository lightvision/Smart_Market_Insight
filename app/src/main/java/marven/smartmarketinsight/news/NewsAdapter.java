package marven.smartmarketinsight.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import marven.smartmarketinsight.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsModel> newsList;

    public NewsAdapter(List<NewsModel> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsModel news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.description.setText(news.getDescription());
        holder.source.setText(news.getSource());

        if (news.getUrl() != null && !news.getUrl().isEmpty()) {
            holder.itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("title", news.getTitle());
                bundle.putString("description", news.getDescription());
                bundle.putString("source", news.getSource());

                Navigation.findNavController(v).navigate(R.id.action_newsFragment_to_newsDetailsFragment, bundle);
            });
            holder.itemView.setAlpha(1.0f);
        } else {
            holder.itemView.setOnClickListener(null);
            holder.itemView.setAlpha(0.6f);
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, source, time;
        ImageView image;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            description = itemView.findViewById(R.id.news_description);
            source = itemView.findViewById(R.id.news_source);
            time = itemView.findViewById(R.id.news_time);
            image = itemView.findViewById(R.id.news_image);
        }
    }

    public void updateNews(List<NewsModel> newNewsList) {
        this.newsList = newNewsList;
        notifyDataSetChanged();
    }
}
