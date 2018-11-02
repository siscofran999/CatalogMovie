package com.example.tsmpc47.catalogmovie.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class FavoriteWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FavoriteWidgetAdapter(this.getApplicationContext(), intent);
    }
}
