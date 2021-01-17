package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.navigationdrawer.ui.mainbmi.MainBMIFragment;

public class MainBMI extends AppCompatActivity {

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main_b_m_i_activity );
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace( R.id.container, MainBMIFragment.newInstance() )
                    .commitNow();
        }

        //validation
        awesomeValidation = new AwesomeValidation( ValidationStyle.BASIC );
        awesomeValidation.addValidation( this, R.id.atm_value, RegexTemplate.NOT_EMPTY, R.string.invalid_name );

    }



}
