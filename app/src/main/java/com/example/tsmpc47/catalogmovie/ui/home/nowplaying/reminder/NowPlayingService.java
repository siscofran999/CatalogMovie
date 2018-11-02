package com.example.tsmpc47.catalogmovie.ui.home.nowplaying.reminder;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.MainThread;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.BuildConfig;
import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.data.model.NowPlaying;
import com.example.tsmpc47.catalogmovie.data.model.Result;
import com.example.tsmpc47.catalogmovie.data.remote.ApiEndPoint;
import com.example.tsmpc47.catalogmovie.ui.main.MainActivity;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NowPlayingService extends GcmTaskService {

    public static String TAG_TASK_NOW_PLAYING = "Now Playing Movie";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final String TAG = "NowPlayingService";

    @Override
    public int onRunTask(TaskParams taskParams) {
        int result = 0;
        if (taskParams.getTag().equals(TAG_TASK_NOW_PLAYING)){
            getNowPlaying();
            result = GcmNetworkManager.RESULT_SUCCESS;
        }
        return result;
    }

    private void getNowPlaying() {
        compositeDisposable.add(getNowPlayingData()
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(nowPlaying -> {
                    if (nowPlaying.getTotalResults() != 0){
                        List<Result> itemsMovie = nowPlaying.getResults();

                        String judul = itemsMovie.get(0).getTitle();
                        String pesan = itemsMovie.get(0).getOverview();
                        int notifId = 100;

                        showNotif(judul,pesan,notifId);
                    }
                }, throwable -> {
                    Log.e(TAG, "accept: "+throwable.getMessage());
                }));
    }

    private void showNotif(String judul, String pesan, int notifId) {
        NotificationManager notificationManagerCompat = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(judul)
                .setContentText(pesan)
                .setColor(ContextCompat.getColor(this, android.R.color.transparent))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        notificationManagerCompat.notify(notifId, builder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        resetRx();
    }

    private void resetRx() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        compositeDisposable = null;
    }

    public Single<NowPlaying> getNowPlayingData() {
        return Rx2AndroidNetworking.get(ApiEndPoint.URL+"movie/now_playing?")
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .addQueryParameter("language","en-US")
                .build()
                .getObjectSingle(NowPlaying.class);
    }
}
