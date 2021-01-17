package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.navigationdrawer.ui.creatinineclearance.CreatinineClearanceFragment;

public class CreatinineClearance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.creatinine_clearance_activity );
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace( R.id.container, CreatinineClearanceFragment.newInstance() )
                    .commitNow();
        }
    }
}
