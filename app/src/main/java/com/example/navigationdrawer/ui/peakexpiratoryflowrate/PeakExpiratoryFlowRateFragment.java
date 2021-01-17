package com.example.navigationdrawer.ui.peakexpiratoryflowrate;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.navigationdrawer.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PeakExpiratoryFlowRateFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private PeakExpiratoryFlowRateViewModel mViewModel;
    AwesomeValidation awesomeValidation = new AwesomeValidation( ValidationStyle.BASIC );
    DecimalFormat decimalFormat = new DecimalFormat( "#.#" );
    RadioGroup radioGroup;
    String gender;

    public static PeakExpiratoryFlowRateFragment newInstance() {
        return new PeakExpiratoryFlowRateFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.peak_expiratory_flow_rate_fragment, container, false );

        final EditText age_value = root.findViewById( R.id.pfr_age_value );
        final EditText height_value = root.findViewById( R.id.pfr_height_value );
        final EditText result_value = root.findViewById( R.id.pfr_result );

        awesomeValidation.addValidation( age_value, RegexTemplate.NOT_EMPTY, "enter value" );
        awesomeValidation.addValidation( height_value, RegexTemplate.NOT_EMPTY, "enter value" );

        Button calculate = root.findViewById( R.id.pfr_calculate );
        radioGroup = root.findViewById( R.id.pfr_radioGroup );
        radioGroup.setOnCheckedChangeListener( this );



        /*PEFFemale = e((0.376*ln(Age))-(0.012*Age)-(58.8/Height)+5.63)
        PEFMale = e((0.544*ln(Age))-(0.0151*Age)-(74.7/Height)+5.48)*/
        /*Children PEFR = ((Height in cm - 100) x 5) + 100
            Adult Men = (((Height in m x 5.48) + 1.58) - (Age x 0.041)) x 60
            Adult Women = (((Height in m x 3.72) + 2.24) - (Age x 0.03)) x 60*/

        calculate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int genderSelected = radioGroup.getCheckedRadioButtonId();
                if (genderSelected != -1) {
                    if (awesomeValidation.validate()) {
                        Double age = Double.parseDouble( age_value.getText().toString() );
                        Double height = Double.parseDouble( height_value.getText().toString() );
                        Double pfr = 0.0;

                        switch (gender) {
                            case "male":
                                pfr = (((height * 5.48) + 1.58) - (age * 0.041)) * 60;
                                break;
                            case "female":
                                pfr = (((height * 3.72) + 2.24) - (age * 0.03)) * 60;
                                break;
                        }

                        decimalFormat.setRoundingMode( RoundingMode.CEILING );
                        result_value.setVisibility( v.getVisibility() );
                        result_value.setText( decimalFormat.format( pfr ) );
                    }
                } else {
                    Toast.makeText( getContext(),"Select Gender Please",Toast.LENGTH_LONG ).show();
                }
            }
        } );

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        mViewModel = ViewModelProviders.of( this ).get( PeakExpiratoryFlowRateViewModel.class );
        // TODO: Use the ViewModel
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.pfr_male:
                gender = "male";
            case R.id.pfr_female:
                gender = "female";
        }
    }
}
