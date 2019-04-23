package com.rjz.viewmodeldemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CounterViewModel viewModel =
                ViewModelProviders.of(MainActivity.this).get(CounterViewModel.class);

        final TextView counter = findViewById(R.id.counter);
        final TextView VMcounter = findViewById(R.id.VMcounter);
        Button button = findViewById(R.id.button);
        Button VMbutton = findViewById(R.id.VMbutton);

        VMcounter.setText(String.valueOf(viewModel.getCounter()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(counter.getText().toString());
                count++;
                counter.setText(String.valueOf(count));
            }
        });

        VMbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addValue();
//                VMcounter.setText(String.valueOf(viewModel.getCounter()));
            }
        });

        // live data Added 3
        viewModel.getLiveCounter().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                // update UI using LiveData.
                VMcounter.setText(String.valueOf(integer));
            }
        });
    }
}
