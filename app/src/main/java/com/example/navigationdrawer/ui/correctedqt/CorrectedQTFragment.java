package com.example.navigationdrawer.ui.correctedqt;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.service.autofill.RegexValidator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.navigationdrawer.CorrectedQT;
import com.example.navigationdrawer.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CorrectedQTFragment extends Fragment {

    private CorrectedQTViewModel mViewModel;

    public static CorrectedQTFragment newInstance() {
        return new CorrectedQTFragment();
    }


    AwesomeValidation awesomeValidation;
    DecimalFormat decimalFormat = new DecimalFormat( "###.##" );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.corrected_q_t_fragment, container, false );

        Button calculate = view.findViewById( R.id.qt_calculate );
        final EditText heart_rate_value = view.findViewById( R.id.qt_heart_rate_value );
        final EditText qt_interval_Value =view.findViewById( R.id.qt_interval_value );
        final EditText qt_result = view.findViewById( R.id.qt_result );

        awesomeValidation = new AwesomeValidation( ValidationStyle.BASIC );
        awesomeValidation.addValidation( heart_rate_value, RegexTemplate.NOT_EMPTY, "enter value" );
        awesomeValidation.addValidation( qt_interval_Value, RegexTemplate.NOT_EMPTY, "enter value" );

        calculate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RR_interval =  60/HR
                //Framingham Formula: QTc = QT interval + 154 x (1 - RR_interval)
                if(awesomeValidation.validate()){
                    Double HR = Double.parseDouble( heart_rate_value.getText().toString() );
                    Double QT_interval =Double.parseDouble( qt_interval_Value.getText().toString() );

                    Double RR_interval = 60/HR;
                    Double QTC = QT_interval + 154 * (1-RR_interval);

                    decimalFormat.setRoundingMode( RoundingMode.CEILING );
                    qt_result.setVisibility( v.getVisibility() );
                    qt_result.setText( decimalFormat.format( QTC ) );
                }
            }
        } );

        //cc=QT+1.75*(HR-60)


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        mViewModel = ViewModelProviders.of( this ).get( CorrectedQTViewModel.class );
        // TODO: Use the ViewModel
    }

}
