package com.example.navigationdrawer.ui.fractionalexcretionofsodium;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigationdrawer.R;

public class FractionalExcretionOfSodiumFragment extends Fragment {

    private FractionalExcretionOfSodiumViewModel mViewModel;

    public static FractionalExcretionOfSodiumFragment newInstance() {
        return new FractionalExcretionOfSodiumFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fractional_excretion_of_sodium_fragment, container, false );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        mViewModel = ViewModelProviders.of( this ).get( FractionalExcretionOfSodiumViewModel.class );
        // TODO: Use the ViewModel
    }

}
