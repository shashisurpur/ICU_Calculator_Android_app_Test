package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.navigationdrawer.ui.fractionalexcretionofsodium.FractionalExcretionOfSodiumFragment;

public class FractionalExcretionOfSodium extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.fractional_excretion_of_sodium_activity );
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace( R.id.container, FractionalExcretionOfSodiumFragment.newInstance() )
                    .commitNow();
        }
    }
}
