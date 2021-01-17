package com.example.navigationdrawer.ui.creatinineclearance;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.navigationdrawer.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CreatinineClearanceFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private CreatinineClearanceViewModel mViewModel;
    AwesomeValidation awesomeValidation;
    DecimalFormat decimalFormat = new DecimalFormat( "#.##" );
    RadioGroup radioGroup;
    RadioButton radioButton;
    String gender;

    public static CreatinineClearanceFragment newInstance() {
        return new CreatinineClearanceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate( R.layout.creatinine_clearance_fragment, container, false );

        final EditText age_value = root.findViewById( R.id.cc_age_value );
        final EditText weight_value = root.findViewById( R.id.cc_weight_value );
        final EditText serum_creatinine_value = root.findViewById( R.id.cc_serum_creatinine_value );
        final EditText cc_result = root.findViewById( R.id.cc_result );
        radioGroup = root.findViewById( R.id.cc_radio_group );
        radioGroup.setOnCheckedChangeListener( this );
        Button calculate = root.findViewById( R.id.cc_calculate );


        awesomeValidation = new AwesomeValidation( ValidationStyle.BASIC );
        awesomeValidation.addValidation( age_value, RegexTemplate.NOT_EMPTY, "enter value" );
        awesomeValidation.addValidation( weight_value, RegexTemplate.NOT_EMPTY, "enter value" );
        awesomeValidation.addValidation( serum_creatinine_value, RegexTemplate.NOT_EMPTY, "enter value" );


        calculate.setOnClickListener( new View.OnClickListener() {

            //Cockcroft-Gault CrCl, mL/min = (140 – age) × (weight, kg) × (0.85 if female) / (72 × Cr)
            @Override
            public void onClick(View v) {

                int isSelected = radioGroup.getCheckedRadioButtonId();
                if (isSelected != -1) {
                    if (awesomeValidation.validate()) {
                        Double age = Double.parseDouble( age_value.getText().toString() );
                        Double weight = Double.parseDouble( weight_value.getText().toString() );
                        Double serum = Double.parseDouble( serum_creatinine_value.getText().toString() );
                        Double CrcL;
                        if (gender == "female") {
                            CrcL = (140 - age) * (weight) * (0.85) / (72 * serum);
                        } else {
                            CrcL = (140 - age) * (weight) / (72 * serum);
                        }
                        decimalFormat.setRoundingMode( RoundingMode.CEILING );
                        cc_result.setVisibility( v.getVisibility() );
                        cc_result.setText( decimalFormat.format( CrcL ) );

                    }
                } else {
                    Toast.makeText( getContext(), "Select Gender Please", Toast.LENGTH_SHORT ).show();
                }
            }
        } );


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        mViewModel = ViewModelProviders.of( this ).get( CreatinineClearanceViewModel.class );
        // TODO: Use the ViewModel
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.male:
                gender = "male";
                break;
            case R.id.female:
                gender = "female";
                break;
        }

    }
}
