package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.navigationdrawer.ui.oxygenationindex.OxygenationIndexFragment;

public class OxygenationIndex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.oxygenation_index_activity );
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace( R.id.container, OxygenationIndexFragment.newInstance() )
                    .commitNow();
        }
    }
}
