package com.example.navigationdrawer.ui.freewaterdeficit;

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

public class FreeWaterDeficitFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private FreeWaterDeficitViewModel mViewModel;
    AwesomeValidation awesomeValidation = new AwesomeValidation( ValidationStyle.BASIC );
    DecimalFormat decimalFormat = new DecimalFormat( "#.#" );
    String radio_value, radio_value1;
    RadioGroup radioGroup1, radioGroup2;
    RadioButton radioButton;

    public static FreeWaterDeficitFragment newInstance() {
        return new FreeWaterDeficitFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.free_water_deficit_fragment, container, false );
        final EditText current_na_value = root.findViewById( R.id.fwd_current_Na_value );
        final EditText weight_value = root.findViewById( R.id.fwd_weight_value );
        final EditText idial_Na_value = root.findViewById( R.id.fwd_idial_Na_value );
        final EditText result_value = root.findViewById( R.id.fwd_result );

        Button calculate = root.findViewById( R.id.fwd_calculate );
        //Radio Button
        radioGroup1 = root.findViewById( R.id.fwd_radioGroup );
        radioGroup2 = root.findViewById( R.id.fwd_radioGroup1 );
        radioGroup1.setOnCheckedChangeListener( this );
        radioGroup2.setOnCheckedChangeListener( this );
        //Validation
        awesomeValidation.addValidation( current_na_value, RegexTemplate.NOT_EMPTY, "enter value" );
        awesomeValidation.addValidation( idial_Na_value, RegexTemplate.NOT_EMPTY, "enter value" );
        awesomeValidation.addValidation( weight_value, RegexTemplate.NOT_EMPTY, "enter value" );

        /* Free water deficit = % total body water, fraction × weight, kg × (current Na - ideal Na/ ideal Na)
            where % total body water (TBW) is:
            child male: 60% (0.59)
            child female:50% (0.57)
            Adult male: 60% (i.e., use 0.6 in the equation)
            Adult female: 50% (0.5)
            Elderly male: 50% (0.5)
            Elderly female: 45% (0.45)
            Child: 60% (0.6)*/

        calculate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int genderSelected = radioGroup2.getCheckedRadioButtonId();
                int categorySelected = radioGroup1.getCheckedRadioButtonId();

                if (genderSelected != -1 && categorySelected != -1) {
                    if (awesomeValidation.validate()) {
                        Double weight = Double.parseDouble( weight_value.getText().toString() );
                        Double current_na = Double.parseDouble( current_na_value.getText().toString() );
                        Double idial_Na = Double.parseDouble( idial_Na_value.getText().toString() );
                        Double fwd = 0.00;

                        switch (radio_value1) {
                            case "male":
                                switch (radio_value) {
                                    case "child":
                                        fwd = (0.59) * weight * ((current_na - idial_Na) / idial_Na);
                                        break;
                                    case "adult":
                                        fwd = (0.6) * weight * ((current_na - idial_Na) / idial_Na);
                                        break;
                                    case "elderly":
                                        fwd = (0.5) * weight * ((current_na - idial_Na) / idial_Na);
                                        break;
                                }
                                break;
                            case "female":
                                switch (radio_value) {
                                    case "child":
                                        fwd = (0.57) * weight * ((current_na - idial_Na) / idial_Na);
                                        break;
                                    case "adult":
                                        fwd = (0.5) * weight * ((current_na - idial_Na) / idial_Na);
                                        break;
                                    case "elderly":
                                        fwd = (0.45) * weight * ((current_na - idial_Na) / idial_Na);
                                        break;
                                }
                                break;
                        }
                        decimalFormat.setRoundingMode( RoundingMode.CEILING );
                        result_value.setVisibility( v.getVisibility() );
                        result_value.setText( decimalFormat.format( fwd ) );
                    }
                } else if (categorySelected == -1) {
                    Toast.makeText( getContext(), "Select Category", Toast.LENGTH_LONG ).show();
                } else if (genderSelected == -1) {
                    Toast.makeText( getContext(), "Select Gender", Toast.LENGTH_LONG ).show();
                } else {
                    Toast.makeText( getContext(), "Select Gender and Category", Toast.LENGTH_LONG ).show();
                }

            }
        } );

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        mViewModel = ViewModelProviders.of( this ).get( FreeWaterDeficitViewModel.class );
        // TODO: Use the ViewModel
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.fwd_radioChild:
                radio_value = "child";
                break;
            case R.id.fwd_radioAdult:
                radio_value = "adult";
                break;
            case R.id.fwd_radioEdlerly:
                radio_value = "elderly";
                break;
            case R.id.fwd_radioMale:
                radio_value1 = "male";
                break;
            case R.id.fwd_radioFemale:
                radio_value1 = "female";
                break;
        }
    }
}
