package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.navigationdrawer.ui.correctedqt.CorrectedQTFragment;

public class CorrectedQT extends AppCompatActivity {

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.corrected_q_t_activity );
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace( R.id.container, CorrectedQTFragment.newInstance() )
                    .commitNow();
        }
        //Validation
        awesomeValidation =new AwesomeValidation( ValidationStyle.BASIC );
        awesomeValidation.addValidation( this, R.id.qt_heart_rate, RegexTemplate.NOT_EMPTY,R.string.invalid_name );
        awesomeValidation.addValidation( this, R.id.qt_interval_value, RegexTemplate.NOT_EMPTY,R.string.invalid_name );


    }
}
