package marven.smart_market_insight.ui.news;

                    import android.view.LayoutInflater;
                    import android.view.View;
                    import android.view.ViewGroup;
                    import android.widget.TextView;

                    import androidx.annotation.NonNull;
                    import androidx.recyclerview.widget.RecyclerView;

                    import java.util.List;

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

                            public NewsViewHolder(@NonNull View itemView) {
                                super(itemView);
                                title = itemView.findViewById(R.id.news_title);
                                description = itemView.findViewById(R.id.news_description);
                            }
                        }
                    }