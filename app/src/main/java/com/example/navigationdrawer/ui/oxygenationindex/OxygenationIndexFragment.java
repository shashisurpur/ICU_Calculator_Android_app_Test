package com.example.navigationdrawer.ui.oxygenationindex;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.ViewPagerAdapter;
import com.example.navigationdrawer.ui.bms.BMSFragment;
import com.example.navigationdrawer.ui.correctedqt.CorrectedQTFragment;
import com.google.android.material.tabs.TabLayout;

public class OxygenationIndexFragment extends Fragment {

    private OxygenationIndexViewModel mViewModel;


    public static OxygenationIndexFragment newInstance() {
        return new OxygenationIndexFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.oxygenation_index_fragment, container, false );

        final TabLayout tabLayout = root.findViewById( R.id.tabMode_oif );
        final ViewPager viewPager = root.findViewById( R.id.viewPager_oif );

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager() );
        viewPagerAdapter.AddFragment( new calculatorOxygenation(), "Calculator");
        viewPagerAdapter.AddFragment( new CorrectedQTFragment(),"View Stats" );
        //set adapter
        viewPager.setAdapter( viewPagerAdapter );
        tabLayout.setupWithViewPager( viewPager );

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        mViewModel = ViewModelProviders.of( this ).get( OxygenationIndexViewModel.class );
        // TODO: Use the ViewModel
    }

}
