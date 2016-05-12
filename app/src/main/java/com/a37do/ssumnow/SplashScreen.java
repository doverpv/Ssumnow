package com.a37do.ssumnow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by hhylu on 2016-05-12.
 */
public class SplashScreen extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
