package com.sinfeeloo.monthpickerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sinfeeloo.monthpicker.MonthPicker;
public class MainActivity extends AppCompatActivity {

    private MonthPicker monthPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monthPicker = findViewById(R.id.monthPicker);
        monthPicker.setOnMonthSelectEventListener(new MonthPicker.OnMonthSelectEventListener() {
            @Override
            public void onMonthSelected(String year, String month) {
                Toast.makeText(MainActivity.this, year + "年" + month + "月", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
