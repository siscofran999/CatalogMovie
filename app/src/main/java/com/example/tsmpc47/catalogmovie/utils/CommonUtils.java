/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.example.tsmpc47.catalogmovie.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.R;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amitshekhar on 07/07/17.
 */

public final class CommonUtils {
    private static final String TAG = "CommonUtils";

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static String cutText(String overview){
        String deskripsi;
        if (overview.length() < 30){
            deskripsi = overview;
        }else{
            String text = overview.substring(0, 30);
            String text2 = overview.substring(31);
            String titik2 = text2.replace(text2, "...");
            deskripsi = text+titik2;
        }
        return deskripsi;
    }

    public static String converDate(String date){

        if (date.equals("")){
            Log.i(TAG, "converDate: Masuk Sini");
            return "";
        }else{
            Log.i(TAG, "converDate: Masuk Sana : "+date);
            DateFormat inputFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            try {
                date1 = inputFormatter1.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            DateFormat outputFormatter1 = new SimpleDateFormat("EEE, MMM d, yyyy");
            String output1 = outputFormatter1.format(date1);

            return output1;
        }
    }

}
