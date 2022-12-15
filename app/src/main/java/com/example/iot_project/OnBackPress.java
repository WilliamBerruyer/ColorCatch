package com.example.iot_project;

import android.app.Activity;
import android.view.View;

public class OnBackPress extends Activity implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        super.finish();
    }
}
