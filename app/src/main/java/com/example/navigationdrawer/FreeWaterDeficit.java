package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.navigationdrawer.ui.freewaterdeficit.FreeWaterDeficitFragment;

public class FreeWaterDeficit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.free_water_deficit_activity );
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace( R.id.container, FreeWaterDeficitFragment.newInstance() )
                    .commitNow();
        }
    }
}
