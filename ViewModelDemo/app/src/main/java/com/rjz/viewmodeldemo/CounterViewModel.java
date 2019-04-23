package com.rjz.viewmodeldemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {

    private int counter = 0;

    // live data Added 1
    private MutableLiveData<Integer> counterVal = new MutableLiveData<>();

    public void addValue() {
        counter++;
        counterVal.setValue(counter);
    }

    public int getCounter() {
        return counter;
    }

    // live data Added 2
    public MutableLiveData<Integer> getLiveCounter() {
        return counterVal;
    }
}
