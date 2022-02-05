package com.forbitbd.lawyersdiary.ui.addcase;

import android.os.Bundle;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.forbitbd.lawyersdiary.utils.Constant;
import com.stepstone.stepper.StepperLayout;

public class AddCaseActivity extends BaseActivity implements Comm {

    private StepperLayout stepperLayout;
    private Dashboard dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);

        dashboard = (Dashboard) getIntent().getSerializableExtra(Constant.DASHBOARD);

        setupToolbar(R.id.toolbar);
        stepperLayout = findViewById(R.id.stepper_layout);
        StepperAdapter adapter = new StepperAdapter(getSupportFragmentManager(),this);
        stepperLayout.setAdapter(adapter);

    }

    @Override
    public Dashboard getDashBoard() {
        return dashboard;
    }
}
