package com.forbitbd.lawyersdiary.ui.calender;

import android.os.Bundle;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class CalenderActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        setupToolbar(R.id.toolbar);

        ChipGroup chipGroup = findViewById(R.id.chip_group);


        String[] holidays = {"Mon, Feb 21",
                "Thu, Mar 17",
                "Likely Mar 18–19",
                "Sat, Mar 26",
                "Likely Apr 28–29",
                "Sun, May 1",
                "Likely May 2–3",
                "Likely Jul 9–10",
                "Likely Aug 7–8",
                "Mon, Aug 15",
                "Thu, Aug 18"};

        for(String holiday : holidays) {
            Chip chip = new Chip(this);
            chip.setText(holiday);
            chipGroup.addView(chip);
        }
    }
}