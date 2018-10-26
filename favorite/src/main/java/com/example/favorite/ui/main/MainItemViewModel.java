package com.example.favorite.ui.main;

import android.databinding.ObservableField;

import com.example.favorite.BuildConfig;
import com.example.favorite.data.DataManager;
import com.example.favorite.data.model.Result;
import com.example.favorite.ui.base.BaseViewModel;
import com.example.favorite.utils.rx.SchedulerProvider;

import static com.example.favorite.utils.CommonUtils.converDate;
import static com.example.favorite.utils.CommonUtils.cutText;

public class MainItemViewModel extends BaseViewModel {

    private Result mResult;
    private ItemFavoriteListener mListener;
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> judulnow = new ObservableField<>();
    public ObservableField<String> descnow = new ObservableField<>();
    public ObservableField<String> datenow = new ObservableField<>();
    public ObservableField<String> star = new ObservableField<>();

    public MainItemViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setDataItem(Result result, ItemFavoriteListener listener){
        this.mResult = result;
        mListener = listener;
        this.imageUrl.set(BuildConfig.POSTER+result.getPosterPath());
        this.judulnow.set(mResult.getTitle());
        this.descnow.set(cutText(mResult.getOverview()));
        this.datenow.set(converDate(mResult.getReleaseDate()));
        this.star.set(String.valueOf(result.getVoteAverage()));
    }

    public void share(){
        mListener.shareButton("Ada Film "+mResult.getTitle()+" di bioskop nih, yuk pergi nonton");
    }

    public interface ItemFavoriteListener {

        void shareButton(String judul);
    }
}
