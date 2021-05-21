package com.example.phisicscatalog.ui.electrodinamic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ElectroViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ElectroViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}