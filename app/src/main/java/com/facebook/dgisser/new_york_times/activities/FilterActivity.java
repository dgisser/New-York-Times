package com.facebook.dgisser.new_york_times.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.facebook.dgisser.new_york_times.DatePickerFragment;
import com.facebook.dgisser.new_york_times.Models.Settings;
import com.facebook.dgisser.new_york_times.R;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dgisser on 6/23/16.
 */
public class FilterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    @BindView(R.id.datePickerBtn) Button dpDate;
    @BindView(R.id.spinner) Spinner spOrder;
    int flags = 0;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox_art:
                if (checked)
                    flags += 1;
                break;
            case R.id.checkBox_fashion:
                if (checked)
                    flags += 2;
                break;
            case R.id.checkBox_sports:
                if (checked)
                    flags += 4;
                break;
        }
    }

    public void saveFilters(View v) {
        Settings settings = new Settings(flags, spOrder.getSelectedItemPosition(), date);
        Intent data = new Intent();
        data.putExtra("settings", Parcels.wrap(settings));
        setResult(RESULT_OK, data);
        finish();
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Log.d("filteractivity", String.format("day %s",dayOfMonth));
        // store the values selected into a Calendar instance
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        date = format.format(c.getTime());
    }
}
