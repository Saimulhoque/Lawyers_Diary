package com.forbitbd.lawyersdiary.ui.appointment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.AppointmentRequest;
import com.forbitbd.lawyersdiary.model.AppointmentResponse;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.ui.addappointment.AddAppointmentFragment;
import com.forbitbd.lawyersdiary.ui.addappointment.ResponseCom;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.forbitbd.lawyersdiary.utils.Constant;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AppointmentActivity extends BaseActivity implements AppointmentContract.View, ResponseCom {

    private AppointmentPresenter mPresenter;
    private ExtendedFloatingActionButton btnEFAB;
    private Dashboard dashboard;
    private RecyclerView recyclerView;
    private ArrayList<AppointmentResponse> appointmentResponseList;
    private AppointmentAdapter adapter;
    private static final int REQUEST_CALL = 1;

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
        appointmentResponseList = new ArrayList<>();
        adapter = new AppointmentAdapter(appointmentResponseList, new AppointmentAdapter.CallClickListener() {
            @Override
            public void OnCallClick(AppointmentResponse appointmentResponse) {
                dialCall(appointmentResponse.getClient().getPhone_one(),appointmentResponse);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void dialCall(String phone_one, AppointmentResponse appointmentResponse) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Did you want to call, "+appointmentResponse.getClient().getName()+"?");
        builder.setCancelable(true);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("tel:" + phone_one));
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert11 = builder.create();
        alert11.show();
    }

    @Override
    public void addAppointments(List<AppointmentResponse> appointmentResponses) {
        for (AppointmentResponse x : appointmentResponses) {
            adapter.AddAppointments(x);
        }
    }

    @Override
    public void saveAppointment(AppointmentResponse appointmentResponse) {
        adapter.AddAppointments(appointmentResponse);
    }
}