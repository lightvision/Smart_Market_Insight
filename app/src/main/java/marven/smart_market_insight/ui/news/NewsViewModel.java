package marven.smart_market_insight.ui.news;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Context;

import marven.smart_market_insight.R;

public class NewsViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        Context context = getApplication().getApplicationContext();
        mText.setValue(context.getString(R.string.news_fragment_text));
    }

    public LiveData<String> getText() {
        return mText;
    }
}