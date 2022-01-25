package com.forbitbd.lawyersdiary.ui.addcase;

import android.os.Bundle;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.stepstone.stepper.StepperLayout;

public class AddCaseActivity extends BaseActivity {

    private StepperLayout stepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);

        setupToolbar(R.id.toolbar);
        stepperLayout = findViewById(R.id.stepper_layout);
        StepperAdapter adapter = new StepperAdapter(getSupportFragmentManager(),this);
        stepperLayout.setAdapter(adapter);

    }
}