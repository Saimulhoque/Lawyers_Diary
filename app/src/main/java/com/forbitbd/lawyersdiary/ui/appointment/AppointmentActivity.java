package com.forbitbd.lawyersdiary.ui.appointment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Appointment;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.ui.addappointment.AddAppointmentFragment;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.forbitbd.lawyersdiary.utils.Constant;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class AppointmentActivity extends BaseActivity implements AppointmentContract.View{

    private AppointmentPresenter mPresenter;
    private ExtendedFloatingActionButton btnEFAB;
    private Dashboard dashboard;
    private RecyclerView recyclerView;
    private ArrayList<Appointment> appointmentList;
    private AppointmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        mPresenter = new AppointmentPresenter(this);
        setupToolbar(R.id.toolbar);
        dashboard = (Dashboard) getIntent().getSerializableExtra(Constant.DASHBOARD);
        btnEFAB = findViewById(R.id.add_appointment);

        btnEFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAppointmentFragment fragment = new AddAppointmentFragment();
                fragment.setCancelable(true);
                Bundle data = new Bundle();
                data.putSerializable("dashboard",dashboard);
                fragment.setArguments(data);
                fragment.show(getSupportFragmentManager(),"KKKKKKK");
            }
        });

        String lawyerId = AppPreference.getInstance(this).getLawyer().get_id();
        mPresenter.getAppointments(lawyerId);
        initView();

    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        appointmentList = new ArrayList<>();
        adapter = new AppointmentAdapter(appointmentList);
        recyclerView.setAdapter(adapter);
    }
}