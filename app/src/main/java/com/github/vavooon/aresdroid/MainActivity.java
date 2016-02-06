package com.github.vavooon.aresdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        prepareMainWindow();
    }

    private void prepareMainWindow() {
        /**
         * TODO: move current option to settings
         */
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
