package com.github.vavooon.aresdroid;

import android.content.Context;
import android.os.Build;

public class AresHelper {
    public static final String LOG_TAG = AresHelper.class.getSimpleName();

    public int currentSdk;
    public AresDbHelper db;
    public AresDisplayHelper display;

    public AresHelper(Context context) {
        AresLog.d(LOG_TAG, "constructor");

        this.currentSdk = Build.VERSION.SDK_INT;
        this.db = new AresDbHelper(context);
        this.display = new AresDisplayHelper(context);
    }
}
