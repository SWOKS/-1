package com.example.phisicscatalog.ui.mehanica;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MehanicaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MehanicaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}