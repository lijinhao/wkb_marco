package com.eduonline.app.d7.ap;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.eduonline.app.d7.app.R;

public class MainPreferenceActivity extends PreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main);
        //this.getSharedPreferences("com.eduonline.app.d7.ap.MainPreferenceActivity")
    }
}
