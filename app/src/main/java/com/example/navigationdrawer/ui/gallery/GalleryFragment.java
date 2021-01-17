package com.example.navigationdrawer.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.navigationdrawer.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    AwesomeValidation awesomeValidation;

    DecimalFormat decimalFormat = new DecimalFormat( "##,###.##" );

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of( this ).get( GalleryViewModel.class );
        View root = inflater.inflate( R.layout.fragment_gallery, container, false );

        final TextView textView = root.findViewById( R.id.text1 );

        final TextView atm_value = root.findViewById( R.id.atm_value );
        final EditText pao2_value = root.findViewById( R.id.pao2_value );
        final EditText paco2_value = root.findViewById( R.id.paco2_value );
        final TextView lable_value = root.findViewById( R.id.lable_value );
        final TextView h2o_value = root.findViewById( R.id.h2o_value );
        final EditText age_value = root.findViewById( R.id.age_value );

        final EditText result = root.findViewById( R.id.result );
        final EditText ex_result =root.findViewById( R.id.ex_result );

        Button calculate_O2_Gradient =root.findViewById( R.id.calculate_A_a_O2 );

        //Valdation


        galleryViewModel.getText().observe( getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText( s );
            }
        } );



        calculate_O2_Gradient.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                awesomeValidation = new AwesomeValidation( ValidationStyle.BASIC );
                awesomeValidation.addValidation( pao2_value,RegexTemplate.NOT_EMPTY,"enter value" );
                awesomeValidation.addValidation( paco2_value,RegexTemplate.NOT_EMPTY,"enter value" );
                awesomeValidation.addValidation( age_value,RegexTemplate.NOT_EMPTY,"enter value" );
                if(awesomeValidation.validate()) {
                    double fiO2 = Double.parseDouble( lable_value.getText().toString() );
                    double P_atm = Double.parseDouble( atm_value.getText().toString() );
                    double P_h20 = Double.parseDouble( h2o_value.getText().toString() );
                    double P_aco2 = Double.parseDouble( paco2_value.getText().toString() );
                    double P_ao2 = Double.parseDouble( pao2_value.getText().toString() );
                    double age = Double.parseDouble( age_value.getText().toString() );

                    double fi_O2 = fiO2 / 100;
                    double Exres, diff, div, P_A_O2;
                    float out;
                    diff = (P_atm - P_h20);
                    div = (P_aco2 / 0.8);
                    P_A_O2 = (fi_O2 * (P_atm -P_h20 ) - (P_aco2 / 0.8)) - (P_ao2);

                    Exres =(age/4)+4;

                    decimalFormat.setRoundingMode( RoundingMode.CEILING );

                    result.setVisibility( v.getVisibility() );
                    result.setText( String.valueOf( decimalFormat.format(  P_A_O2) ) );

                    ex_result.setVisibility( v.getVisibility() );
                    ex_result.setText( String.valueOf( Exres ) );
                }
            }
        } );

        return root;
    }
}
