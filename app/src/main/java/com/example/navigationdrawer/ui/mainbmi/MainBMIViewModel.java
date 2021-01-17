package com.example.navigationdrawer.ui.mainbmi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainBMIViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText1,mText2;

    public MainBMIViewModel(){
        mText1 = new MutableLiveData<>(  );
        mText2 = new MutableLiveData<>(  );
        mText1.setValue( "Weight" );
        mText2.setValue( "Height" );
    }

    public LiveData<String> getText1(){
        return mText1;
    }
    public LiveData<String> getText2(){
        return mText2;
    }

}
