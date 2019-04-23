package com.rjz.viewmodeldemo;

import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {

    public int counter = 0;

    public void addValue() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
