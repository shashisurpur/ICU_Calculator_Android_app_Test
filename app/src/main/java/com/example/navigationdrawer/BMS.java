package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.navigationdrawer.ui.bms.BMSFragment;

public class BMS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.b_m__s_activity );
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace( R.id.container, BMSFragment.newInstance() )
                    .commitNow();
        }
    }
}
