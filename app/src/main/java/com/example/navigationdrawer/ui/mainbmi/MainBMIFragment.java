package com.example.navigationdrawer.ui.mainbmi;

import androidx.lifecycle.Observer;
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

public class MainBMIFragment extends Fragment {

    private MainBMIViewModel mViewModel;
    private DecimalFormat decimalFormat = new DecimalFormat( "#.##" );

    public static MainBMIFragment newInstance() {
        return new MainBMIFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of( this ).get( MainBMIViewModel.class );

        View view = inflater.inflate( R.layout.main_b_m_i_fragment, container, false );

        final TextView weight = view.findViewById( R.id.bmi_weight );
        final TextView height = view.findViewById( R.id.bmi_height );

        final EditText weight_value = view.findViewById( R.id.bmi_weight_value );
        final EditText height_value = view.findViewById( R.id.bmi_height_value );
        final EditText result_value = view.findViewById( R.id.bmi_result );

        Button calulate_button = view.findViewById( R.id.bmi_calculate );

        mViewModel.getText1().observe( getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                weight.setText( s );
            }
        } );
        mViewModel.getText2().observe( getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                height.setText( s );
            }
        } );

        calulate_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty( weight_value.getText() ) && TextUtils.isEmpty( height_value.getText() ) ){
                    weight_value.setError( "Enter value" );
                    weight_value.requestFocus();
                    height_value.setError( "Enter value" );
                    height_value.requestFocus();
                }else if(TextUtils.isEmpty( weight_value.getText() )){
                    weight_value.setError( "Enter value" );
                    weight_value.requestFocus();
                }
                else if(TextUtils.isEmpty( height_value.getText() )){
                    height_value.setError( "Enter value" );
                    height_value.requestFocus();
                }
                else {
                    double weight_in_kg = Integer.parseInt( weight_value.getText().toString() );
                    double height_in_cm = Integer.parseInt( height_value.getText().toString() );
                    float height_in_m = (float) (height_in_cm / 100);
                    float new_height = (height_in_m * height_in_m);

                    float BMI = (float) (weight_in_kg) / new_height;

                    decimalFormat.setRoundingMode( RoundingMode.CEILING );

                    result_value.setVisibility( v.getVisibility() );
                    result_value.setText( decimalFormat.format( BMI ) );
                }
            }
        } );
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        mViewModel = ViewModelProviders.of( this ).get( MainBMIViewModel.class );
        // TODO: Use the ViewModel
    }

}
