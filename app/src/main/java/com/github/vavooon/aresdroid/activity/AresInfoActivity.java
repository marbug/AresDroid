package com.github.vavooon.aresdroid.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.vavooon.aresdroid.AresHelper;
import com.github.vavooon.aresdroid.entity.AresValidLanguage;

import java.util.ArrayList;

public class AresInfoActivity extends AppCompatActivity {
    private static final String LOG_TAG = AresInfoActivity.class.getSimpleName();

    private AresHelper aresHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        aresHelper = new AresHelper(this);
        // aresHelper.db.onUpgrade(aresHelper.db.getReadableDatabase(), 1, 2);

        prepareMainWindow();
    }

    private void prepareMainWindow() {
        Log.d(LOG_TAG, "prepareMainWindow");

        /**
         * TODO: move current option to settings
         */
        // requestWindowFeature(Window.FEATURE_NO_TITLE);

        // prepare main view
        ScrollView verticalScroll = new ScrollView(this);
        verticalScroll.setVerticalScrollBarEnabled(true);

        TableLayout innerTable = new TableLayout(this);
        int padding = (int)aresHelper.display.widthPixels / 20;
        if (padding < 10) {
            padding = 10;
        }
        innerTable.setPadding(padding, padding, padding, padding);
        verticalScroll.addView(innerTable);

        // Show current SDK version
        TextView staticLabel = new TextView(this);
        staticLabel.setText("Current SDK version:");
        innerTable.addView(staticLabel);

        staticLabel = new TextView(this);
        staticLabel.setText(Integer.toString(aresHelper.currentSdk));
        staticLabel.setGravity(Gravity.RIGHT);
        innerTable.addView(staticLabel);

        // Show current DB version
        staticLabel = new TextView(this);
        staticLabel.setText("Current Db version:");
        innerTable.addView(staticLabel);

        staticLabel = new TextView(this);
        int dbVersion = aresHelper.db.getDbVersion();
        staticLabel.setText(Integer.toString(dbVersion));
        staticLabel.setGravity(Gravity.RIGHT);
        innerTable.addView(staticLabel);

        // Show needed DB version
        staticLabel = new TextView(this);
        staticLabel.setText("Needed Db version:");
        innerTable.addView(staticLabel);

        staticLabel = new TextView(this);
        staticLabel.setText(Integer.toString(aresHelper.db.DATABASE_VERSION));
        staticLabel.setGravity(Gravity.RIGHT);
        innerTable.addView(staticLabel);

        // Show valid languages
        staticLabel = new TextView(this);
        staticLabel.setText("Valid languages:");
        innerTable.addView(staticLabel);

        ArrayList<AresValidLanguage> validLanguages = aresHelper.db.getValidLanguages();

        for (AresValidLanguage validLanguage : validLanguages) {
            TextView languageLabel = new TextView(this);
            languageLabel.setText(validLanguage.label);
            languageLabel.setGravity(Gravity.RIGHT);
            innerTable.addView(languageLabel);
        }

        this.showDisplayInfo(innerTable, "(default) ");
        
        if (aresHelper.currentSdk >= 17) {
            aresHelper.display.getInfo(this, true);
            this.showDisplayInfo(innerTable, "(real) ");
        }

        // set main view
        setContentView(verticalScroll);
    }
    
    private void showDisplayInfo(TableLayout parentLayout, String prefix) {
        
        // Display width in pixels
        TextView staticLabel = new TextView(this);
        staticLabel.setText(prefix + "Display width, pixels:");
        parentLayout.addView(staticLabel);

        staticLabel = new TextView(this);
        staticLabel.setText(Double.toString(aresHelper.display.widthPixels));
        staticLabel.setGravity(Gravity.RIGHT);
        parentLayout.addView(staticLabel);

        // Display height in pixels
        staticLabel = new TextView(this);
        staticLabel.setText(prefix + "Display height, pixels:");
        parentLayout.addView(staticLabel);

        staticLabel = new TextView(this);
        staticLabel.setText(Double.toString(aresHelper.display.heightPixels));
        staticLabel.setGravity(Gravity.RIGHT);
        parentLayout.addView(staticLabel);

        // Display size in pixels
        staticLabel = new TextView(this);
        staticLabel.setText(prefix + "Display size, pixels:");
        parentLayout.addView(staticLabel);

        staticLabel = new TextView(this);
        staticLabel.setText(Double.toString(aresHelper.display.sizePixels));
        staticLabel.setGravity(Gravity.RIGHT);
        parentLayout.addView(staticLabel);

        // Display density
        staticLabel = new TextView(this);
        staticLabel.setText(prefix + "Display density, dpi:");
        parentLayout.addView(staticLabel);

        staticLabel = new TextView(this);
        staticLabel.setText(Double.toString(aresHelper.display.densityDpi));
        staticLabel.setGravity(Gravity.RIGHT);
        parentLayout.addView(staticLabel);

        // Display xdpi
        staticLabel = new TextView(this);
        staticLabel.setText(prefix + "Display xdpi:");
        parentLayout.addView(staticLabel);

        staticLabel = new TextView(this);
        staticLabel.setText(Double.toString(aresHelper.display.xdpi));
        staticLabel.setGravity(Gravity.RIGHT);
        parentLayout.addView(staticLabel);

        // Display ydpi
        staticLabel = new TextView(this);
        staticLabel.setText(prefix + "Display ydpi:");
        parentLayout.addView(staticLabel);

        staticLabel = new TextView(this);
        staticLabel.setText(Double.toString(aresHelper.display.ydpi));
        staticLabel.setGravity(Gravity.RIGHT);
        parentLayout.addView(staticLabel);

        // Display width in inches
        staticLabel = new TextView(this);
        staticLabel.setText(prefix + "Display width, inches:");
        parentLayout.addView(staticLabel);

        staticLabel = new TextView(this);
        staticLabel.setText(Double.toString(aresHelper.display.widthInches));
        staticLabel.setGravity(Gravity.RIGHT);
        parentLayout.addView(staticLabel);

        // Display height in inches
        staticLabel = new TextView(this);
        staticLabel.setText(prefix + "Display height, inches:");
        parentLayout.addView(staticLabel);

        staticLabel = new TextView(this);
        staticLabel.setText(Double.toString(aresHelper.display.heightInches));
        staticLabel.setGravity(Gravity.RIGHT);
        parentLayout.addView(staticLabel);

        // Display size in inches
        staticLabel = new TextView(this);
        staticLabel.setText(prefix + "Display size, inches:");
        parentLayout.addView(staticLabel);

        staticLabel = new TextView(this);
        staticLabel.setText(Double.toString(aresHelper.display.sizeInches));
        staticLabel.setGravity(Gravity.RIGHT);
        parentLayout.addView(staticLabel);
    }
}
