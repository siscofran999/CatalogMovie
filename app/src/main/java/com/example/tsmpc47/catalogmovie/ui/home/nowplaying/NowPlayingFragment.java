package com.example.tsmpc47.catalogmovie.ui.home.nowplaying;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.ViewModelProviderFactory;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.databinding.FragmentNowPlayingBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseFragment;
import com.example.tsmpc47.catalogmovie.BR;
import com.example.tsmpc47.catalogmovie.utils.NetworkUtils;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class NowPlayingFragment extends BaseFragment<FragmentNowPlayingBinding, NowPlayingViewModel> implements NowPlayingNavigator {

    public static final String TAG = "NowPlayingFragment";
    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    NowPlayingAdapter mNowPlayingAdapter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    private NowPlayingViewModel mNowPlayingViewModel;
    FragmentNowPlayingBinding mFragmentNowPlayingBinding;

    public static final int NOTIFICAITION_ID = 1;

    public static NowPlayingFragment newInstance() {
        Bundle args = new Bundle();
        NowPlayingFragment fragment = new NowPlayingFragment();
        fragment.setArguments(args);
        Log.i(TAG, "newInstance: ");
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentNowPlayingBinding = getViewDataBinding();
        setUp();
        subscribeToLiveData();

        showNotification();
    }

    private void showNotification() {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getContext())
                .setSmallIcon(R.drawable.ic_notifications_white_24dp)
                .setLargeIcon(BitmapFactory
                        .decodeResource(getResources()
                                , R.drawable.ic_notifications_white_24dp))
                .setContentTitle(getResources()
                        .getString(R.string.content_title))
                .setContentText(getResources()
                        .getString(R.string.content_text))
                .setSubText(getResources()
                        .getString(R.string.subtext))
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Objects.requireNonNull(getContext()));
        notificationManagerCompat.notify(NOTIFICAITION_ID, notification.build());
    }

    private void subscribeToLiveData() {
        mNowPlayingViewModel.getNowItemLiveData().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> results) {
                mNowPlayingViewModel.addNowItemsToList(results);
            }
        });
    }

    private void setUp() {
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentNowPlayingBinding.rvnowplaying.setLayoutManager(mLinearLayoutManager);
        mFragmentNowPlayingBinding.rvnowplaying.setItemAnimator(new DefaultItemAnimator());
        mFragmentNowPlayingBinding.rvnowplaying.setAdapter(mNowPlayingAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNowPlayingViewModel.setNavigator(this);
        Log.i(TAG, "onCreate: "+savedInstanceState);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_now_playing;
    }

    @Override
    public NowPlayingViewModel getViewModel() {
        mNowPlayingViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NowPlayingViewModel.class);
        return mNowPlayingViewModel;
    }

    @Override
    public void nullData() {
        Toast.makeText(getActivity(), "Maaf, tidak ada film sekarang", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkConnection() {
        if (!NetworkUtils.isNetworkConnected(getActivity())){
            Toast.makeText(getActivity(), "Maaf, harap cek internet anda", Toast.LENGTH_SHORT).show();
        }
    }
}
