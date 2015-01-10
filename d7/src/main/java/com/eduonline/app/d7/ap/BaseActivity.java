package com.eduonline.app.d7.ap;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by student on 2014/12/20.
 */
public class BaseActivity extends ActionBarActivity {

    public SharedPreferences getBaseSharedPreferences(int mode) {
        return super.getSharedPreferences("wkb", mode);
    }
}
