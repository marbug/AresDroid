package com.github.vavooon.aresdroid;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AresDisplayHelper {
    public static final String LOG_TAG = AresDisplayHelper.class.getSimpleName();

    public double widthPixels;
    public double heightPixels;
    public double sizePixels;
    public double densityDpi;
    public double xdpi;
    public double ydpi;
    public double widthInches;
    public double heightInches;
    public double sizeInches;

    public AresDisplayHelper(Context context) {
        AresLog.d(LOG_TAG, "constructor");

        this.getInfo(context);
    }

    public void getInfo(Context context) {
        this.getInfo(context, false);
    }

    public void getInfo(Context context, boolean isRealInfo) {
        DisplayMetrics displayMetrics = new DisplayMetrics();

        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);

        Display display = windowManager.getDefaultDisplay();

        if (!isRealInfo) {
            display.getMetrics(displayMetrics);
        }
        else {
            display.getRealMetrics(displayMetrics);
        }

        this.widthPixels = (double)displayMetrics.widthPixels;
        this.heightPixels = (double)displayMetrics.heightPixels;
        this.sizePixels = Math.sqrt(this.widthPixels * this.widthPixels + this.heightPixels *  this.heightPixels);
        this.densityDpi = (double)displayMetrics.densityDpi;
        this.xdpi = (double)displayMetrics.xdpi;
        this.ydpi = (double)displayMetrics.ydpi;
        this.widthInches = this.widthPixels / this.xdpi;
        this.heightInches = this.heightPixels / this.ydpi;

        if (this.xdpi == this.ydpi) {
            this.sizeInches = this.sizePixels / this.xdpi;
        }
        else {
            this.sizeInches = Math.sqrt(this.widthInches * this.widthInches + this.heightInches * this.heightInches);
        }
    }
}
