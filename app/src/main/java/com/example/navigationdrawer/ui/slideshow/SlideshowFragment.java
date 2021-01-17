package com.example.navigationdrawer.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.navigationdrawer.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    AwesomeValidation awesomeValidation;
    DecimalFormat decimalFormat = new DecimalFormat( "#.##" );


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of( this ).get( SlideshowViewModel.class );
        View root = inflater.inflate( R.layout.fragment_slideshow, container, false );

        final TextView atn_US = root.findViewById( R.id.atn_US );
        final TextView atn_SC = root.findViewById( R.id.atn_SC );
        final TextView atn_UC = root.findViewById( R.id.atn_UC );
        final TextView atn_result = root.findViewById( R.id.atn_result );

        final EditText atn_US_value = root.findViewById( R.id.atn_US_value );
        final EditText atn_SC_value = root.findViewById( R.id.atn_SC_value );
        final EditText atn_UC_value= root.findViewById( R.id.atn_UC_value );

        Button calculate = root.findViewById( R.id.atn_calculate );

        //Validation
        awesomeValidation = new AwesomeValidation( ValidationStyle.BASIC );
        awesomeValidation.addValidation( atn_US_value, RegexTemplate.NOT_EMPTY,"enter value" );
        awesomeValidation.addValidation( atn_SC_value, RegexTemplate.NOT_EMPTY,"enter value" );
        awesomeValidation.addValidation( atn_UC_value, RegexTemplate.NOT_EMPTY,"enter value" );

/*
        slideshowViewModel.getText().observe( getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText( s );
            }
        } );
        slideshowViewModel.getText1().observe( getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView1.setText( s );
            }
        } );

*/

        calculate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){
                    Double us_value = Double.parseDouble( atn_US_value.getText().toString() );
                    Double sc_value = Double.parseDouble( atn_SC_value.getText().toString() );
                    Double uc_value = Double.parseDouble( atn_UC_value.getText().toString() );
                    Double RFI;

                    //RFI = (US*SC)/UC

                    RFI = (us_value*sc_value)/uc_value;

                    decimalFormat.setRoundingMode( RoundingMode.CEILING );

                    atn_result.setVisibility( v.getVisibility() );
                    atn_result.setText( decimalFormat.format( RFI ) );
                }
            }
        } );


        return root;
    }
}
