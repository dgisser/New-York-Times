package com.facebook.dgisser.new_york_times.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.facebook.dgisser.new_york_times.Models.Settings;
import com.facebook.dgisser.new_york_times.R;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dgisser on 6/23/16.
 */
public class FilterActivity extends AppCompatActivity{

    @BindView(R.id.datePicker) DatePicker dpDate;
    @BindView(R.id.spinner) Spinner spOrder;
    int flags = 0;

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
        Settings settings = new Settings(flags, spOrder.getSelectedItemPosition(),
                dpDate.getYear(), dpDate.getMonth(), dpDate.getMonth());
        Intent data = new Intent();
        data.putExtra("settings", Parcels.wrap(settings));
        setResult(RESULT_OK, data);
        finish();
    }

}
