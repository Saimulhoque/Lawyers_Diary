package com.forbitbd.lawyersdiary.ui.appointment;

import android.os.Bundle;
import android.view.View;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class AppointmentActivity extends BaseActivity implements AppointmentContract.View{

    private AppointmentPresenter mPresenter;
    private ExtendedFloatingActionButton btnEFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        mPresenter = new AppointmentPresenter(this);
        setupToolbar(R.id.toolbar);

        btnEFAB = findViewById(R.id.add_appointment);

        btnEFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }
}