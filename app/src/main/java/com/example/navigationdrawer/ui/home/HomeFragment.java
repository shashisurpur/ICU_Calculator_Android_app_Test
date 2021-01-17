package com.example.navigationdrawer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.gridlayout.widget.GridLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.navigationdrawer.MainBMI;
import com.example.navigationdrawer.R;

import com.example.navigationdrawer.ui.bms.BMSFragment;
import com.example.navigationdrawer.ui.correctedqt.CorrectedQTFragment;
import com.example.navigationdrawer.ui.creatinineclearance.CreatinineClearanceFragment;
import com.example.navigationdrawer.ui.freewaterdeficit.FreeWaterDeficitFragment;
import com.example.navigationdrawer.ui.gallery.GalleryFragment;
import com.example.navigationdrawer.ui.mainbmi.MainBMIFragment;
import com.example.navigationdrawer.ui.peakexpiratoryflowrate.PeakExpiratoryFlowRateFragment;
import com.example.navigationdrawer.ui.slideshow.SlideshowFragment;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    GridLayout gridLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of( this ).get( HomeViewModel.class );
        View root = inflater.inflate( R.layout.fragment_home, container, false );

        GridLayout gridLayout = root.findViewById( R.id.gridlayout );

        setSingleEvent( gridLayout );
/*
        CardView cardView1 = root.findViewById( R.id.card1 );
        CardView card_tabular = root.findViewById( R.id.card_acute_tabular );
        CardView card_bmi = root.findViewById( R.id.card_BMI );
        CardView card_bms = root.findViewById( R.id.card_BMS );
        CardView card_cc = root.findViewById( R.id.card_CC );
        CardView card_qtr = root.findViewById( R.id.card_QT );
        CardView card_fwd = root.findViewById( R.id.card_FWD );
        CardView card_pefr = root.findViewById( R.id.card_PEFR );

        cardView1.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_A_a_O2_Gradient ) );

        card_tabular.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Acute_Tubular_Necrosis ) );

       card_bmi.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Body_Mass_Index ) );

        card_bms.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Body_Mass_Surface ) );

        card_cc.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Creatinine_Clearance ) );

        card_qtr.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Corrected_QT_Interval ) );

        card_fwd.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Free_Water_Deficit ) );

        card_pefr.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Peak_Expiratory_Flow_Rate ) );
*/
        return root;
    }

    private void setSingleEvent(GridLayout gridLayout) {

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            CardView cardView = (CardView) gridLayout.getChildAt( i );
            final int finalI = i;
            switch (finalI) {
                case 0:
                    cardView.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_A_a_O2_Gradient ) );
                    break;
                case 1:
                    cardView.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Acute_Tubular_Necrosis ) );
                    break;
                case 2:
                    cardView.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Body_Mass_Index ) );
                    break;
                case 3:
                    cardView.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Body_Mass_Surface ) );
                    break;
                case 4:
                    cardView.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Creatinine_Clearance ) );
                    break;
                case 5:
                    cardView.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Corrected_QT_Interval ) );
                    break;
                case 6:
                    cardView.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Free_Water_Deficit ) );
                    break;
                case 7:
                    cardView.setOnClickListener( Navigation.createNavigateOnClickListener( R.id.nav_Peak_Expiratory_Flow_Rate ) );
                    break;
                default:
                    return;
            }

        }
    }
}
