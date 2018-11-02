package com.example.tsmpc47.catalogmovie.ui.home.nowplaying.reminder;

import android.content.Context;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;

public class SchedulerTask {
    private GcmNetworkManager mGcmNetworkManager;

    public SchedulerTask(Context context){
        mGcmNetworkManager = GcmNetworkManager.getInstance(context);
    }

    public void createPeriodicTask() {
        Task periodicTask = new PeriodicTask.Builder()
                .setService(NowPlayingService.class)
                .setPeriod(7 * 300)
                .setFlex(10)
                .setTag(NowPlayingService.TAG_TASK_NOW_PLAYING)
                .setPersisted(true)
                .build();
        mGcmNetworkManager.schedule(periodicTask);
    }

    public void cancelPeriodicTask(){
        if (mGcmNetworkManager != null){
            mGcmNetworkManager.cancelTask(NowPlayingService.TAG_TASK_NOW_PLAYING, NowPlayingService.class);
        }
    }
}
