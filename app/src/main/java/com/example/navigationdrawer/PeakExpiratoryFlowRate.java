package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.navigationdrawer.ui.peakexpiratoryflowrate.PeakExpiratoryFlowRateFragment;

public class PeakExpiratoryFlowRate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.peak_expiratory_flow_rate_activity );
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace( R.id.container, PeakExpiratoryFlowRateFragment.newInstance() )
                    .commitNow();
        }
    }
}
