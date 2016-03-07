package com.github.vavooon.aresdroid;

import android.util.Log;

public class AresLog {
    public static int currentLevel = -1;

    public static void d(String tag, String message) {
        log(Log.DEBUG, tag, message);
    }

    public static void log(int level, String tag, String message) {
        if (currentLevel == -1) {
            return;
        }
        Log.println(level, tag, message);
    }
}
