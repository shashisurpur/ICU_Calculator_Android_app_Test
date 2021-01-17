package com.example.navigationdrawer.ui.bms;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BMSViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> weight_value;
    private MutableLiveData<String> height_value;

    public BMSViewModel(MutableLiveData<String> weight_value, MutableLiveData<String> height_value) {
        this.weight_value = weight_value;
        this.height_value = height_value;
    }

    public MutableLiveData<String> getWeight_value() {
        return weight_value;
    }

    public void setWeight_value(MutableLiveData<String> weight_value) {
        this.weight_value = weight_value;
    }

    public MutableLiveData<String> getHeight_value() {
        return height_value;
    }

    public void setHeight_value(MutableLiveData<String> height_value) {
        this.height_value = height_value;
    }
}
