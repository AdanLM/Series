package com.adanlm.series.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CommonUtils {

    public static String cleanHTMLText(String stringToClean) {
        return stringToClean.replaceAll("\\<.*?\\>", "");
    }

    public static String cleanBracketsText(String stringToClean) {
        return stringToClean.replaceAll("[\\[\\]']+", "");
    }

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
