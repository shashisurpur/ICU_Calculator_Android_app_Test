package com.example.navigationdrawer.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.navigationdrawer.R;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;



    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue( "Atmospheric Pressure" );
    }

    public LiveData<String> getText() {
        return mText;
    }
}