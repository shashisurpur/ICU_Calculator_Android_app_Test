package com.example.navigationdrawer.ui.bms;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.navigationdrawer.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BMSFragment extends Fragment {

    private BMSViewModel mViewModel;
    private DecimalFormat decimalFormat = new DecimalFormat( "#.##" );


    public static BMSFragment newInstance() {
        return new BMSFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.b_m_s_fragment, container, false );

        final TextView weight = root.findViewById( R.id.bms_weight );
        final TextView height = root.findViewById( R.id.bms_height );

        final EditText weight_value = root.findViewById( R.id.bms_weight_value );
        final EditText height_value = root.findViewById( R.id.bms_height_value );
        final EditText result_value = root.findViewById( R.id.bms_result );


        Button calulate_button = root.findViewById( R.id.bms_calculate );

        calulate_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty( weight_value.getText() ) && TextUtils.isEmpty( height_value.getText() )){
                    weight_value.setError( "Enter value" );
                    weight_value.requestFocus();
                    height_value.setError( "Enter Value" );
                    height_value.requestFocus();
                }
                else if(TextUtils.isEmpty( weight_value.getText() )){
                    weight_value.setError( "Enter value" );
                    weight_value.requestFocus();
                }else if(TextUtils.isEmpty( height_value.getText() )){
                    height_value.setError( "Enter Value" );
                    height_value.requestFocus();
                }
                else {

                    float weight_in_kg = Float.parseFloat( weight_value.getText().toString() );
                    float height_in_cm = Float.parseFloat( height_value.getText().toString() );

                    float BMS = (height_in_cm * weight_in_kg) / 3600;
                    float newBMS = (float) Math.sqrt( BMS );

                    result_value.setVisibility( v.getVisibility() );
                    decimalFormat.setRoundingMode( RoundingMode.CEILING );
                    result_value.setText( String.valueOf( decimalFormat.format( newBMS ) ) );
                }
            }
        } );

        return root;
    }


}
