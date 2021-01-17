package com.example.navigationdrawer.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText,mText1;
    private MutableLiveData<Integer> inText1,inText2;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText1 =new MutableLiveData<>( );
        mText.setValue( "A =" );
        mText1.setValue( "B =" );
    }

    public void setText(Integer input){
        inText1 =new MutableLiveData<>(  );
        inText1.setValue( input );
    }
    public void setText2(Integer input){
        inText2=new MutableLiveData<>(  );
        inText2.setValue( input );
    }

    public String getInputText1() {
        return inText1.toString();
    }
    public String getInputText2() {
        return inText2.toString();
    }


    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> getText1() {
        return mText1;
    }

}