package com.forbitbd.lawyersdiary.ui.addappointment;

import android.app.AlertDialog;
import android.app.Dialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.AppointmentRequest;
import com.forbitbd.lawyersdiary.model.AppointmentResponse;
import com.forbitbd.lawyersdiary.model.Client;
import com.forbitbd.lawyersdiary.model.Dashboard;

import com.forbitbd.lawyersdiary.model.OthersAppointmentRequest;
import com.forbitbd.lawyersdiary.ui.addcase.Comm;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.forbitbd.lawyersdiary.utils.MyUtil;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class AddAppointmentFragment extends DialogFragment implements AddAppointmentContract.View {

    private TextInputLayout tiClient, tiAppointmentDate, tiPurpose, tiRemarks,tiName,tiContact;
    private TextInputEditText etPurpose, etAppointmentDate, etRemarks,etName,etContact;
    private AutoCompleteTextView etClient;
    private Button btnSave;
    private ImageView ic_close;
    private Dashboard dashboard;
    private AddAppointmentPresenter mPresenter;
    private ResponseCom com;

    private LinearLayout llHide;

    public AddAppointmentFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com = (ResponseCom) getActivity();
        mPresenter = new AddAppointmentPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_appointment, container, false);

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_add_appointment, null);

        this.dashboard = (Dashboard) getArguments().getSerializable("dashboard");

        ic_close = view.findViewById(R.id.ic_close);
        tiClient = view.findViewById(R.id.ti_client);
        tiAppointmentDate = view.findViewById(R.id.ti_appointment_date);
        tiPurpose = view.findViewById(R.id.ti_appointment_purpose);
        tiRemarks = view.findViewById(R.id.ti_remarks);
        tiName = view.findViewById(R.id.ti_name);
        tiContact = view.findViewById(R.id.ti_contact);

        etClient = view.findViewById(R.id.et_client);
        etAppointmentDate = view.findViewById(R.id.et_appointment_date);
        etPurpose = view.findViewById(R.id.et_appointment_purpose);
        etRemarks = view.findViewById(R.id.et_remarks);
        etName = view.findViewById(R.id.et_name);
        etContact = view.findViewById(R.id.et_contact);
        btnSave = view.findViewById(R.id.btn_save);
        llHide = view.findViewById(R.id.hide);

        Client others = new Client();
        others.setName("Others");
        List<Client> cc = new ArrayList<>();
        cc.addAll(dashboard.getClients());
        cc.add(others);

        etClient.setAdapter(new ArrayAdapter<Client>(getContext(), android.R.layout.simple_expandable_list_item_1, cc));

        etClient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==cc.size()-1){
                    tiClient.setVisibility(View.GONE);
                    llHide.setVisibility(View.VISIBLE);
                }
            }
        });

        etAppointmentDate.setText(MyUtil.dateToStr(new Date()));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        long today = MaterialDatePicker.todayInUtcMilliseconds();

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT APPOINTMENT DATE");
        builder.setSelection(today);
        MaterialDatePicker materialDatePicker = builder.build();

        etAppointmentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getChildFragmentManager(), "JJJJJJJ");

            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                etAppointmentDate.setText(materialDatePicker.getHeaderText());
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String appointment_date = etAppointmentDate.getText().toString().trim();
                String purpose = etPurpose.getText().toString().trim();
                String remarks = etRemarks.getText().toString().trim();

                if(llHide.getVisibility()==View.VISIBLE){
                    Log.d("HHHHH","CAll");
                    String name = etName.getText().toString().trim();
                    String conact = etContact.getText().toString().trim();

                    OthersAppointmentRequest appointmentRequest = new OthersAppointmentRequest();
                    appointmentRequest.setName(name);
                    appointmentRequest.setContact(conact);
                    appointmentRequest.setPurpose(purpose);
                    appointmentRequest.setRemarks(remarks);
                    appointmentRequest.setLawyer_id(AppPreference.getInstance(getContext()).getLawyer().get_id());

                    try {
                        appointmentRequest.setAppointment_date(MyUtil.strToDate(appointment_date));
                    } catch (ParseException e) {
                        appointmentRequest.setAppointment_date(null);
                        e.printStackTrace();
                    }

                    boolean valid = mPresenter.validateOthersAppointment(appointmentRequest);

                    if (!valid) {
                        return;
                    }

                    mPresenter.saveOthersAppointment(appointmentRequest);

                }else {
                    String client = etClient.getText().toString().trim();


                    AppointmentRequest appointmentRequest = new AppointmentRequest();
                    appointmentRequest.setClient(getClientObjectId(client));
                    appointmentRequest.setPurpose(purpose);
                    appointmentRequest.setRemarks(remarks);
                    appointmentRequest.setLawyer_id(AppPreference.getInstance(getContext()).getLawyer().get_id());

                    try {
                        appointmentRequest.setAppointment_date(MyUtil.strToDate(appointment_date));
                    } catch (ParseException e) {
                        appointmentRequest.setAppointment_date(null);
                        e.printStackTrace();
                    }

                    boolean valid = mPresenter.validate(appointmentRequest);

                    if (!valid) {
                        return;
                    }

                    mPresenter.saveAppointment(appointmentRequest);
                }




            }
        });

        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Dialog_Alert).create();
        alertDialog.setView(view);
        return alertDialog;
    }

    public String getClientObjectId(String client) {
        for (Client x : dashboard.getClients()) {
            if (x.getName().equals(client)) {
                return x.get_id();
            }
        }
        return null;
    }

    @Override
    public void clearError() {
        tiClient.setErrorEnabled(false);
        tiAppointmentDate.setErrorEnabled(false);
        tiPurpose.setErrorEnabled(false);
        tiName.setErrorEnabled(false);
        tiContact.setErrorEnabled(false);
    }

    @Override
    public void setError(int fieldId, String message) {
        if (fieldId == 1) {
            tiClient.setError(message);
            etClient.requestFocus();
        } else if (fieldId == 2) {
            tiAppointmentDate.setError(message);
            etAppointmentDate.requestFocus();
        } else if (fieldId == 3) {
            tiPurpose.setError(message);
            etPurpose.requestFocus();
        }else if (fieldId == 4) {
            tiName.setError(message);
            etName.requestFocus();
        }else if (fieldId == 5) {
            tiContact.setError(message);
            etContact.requestFocus();
        }
    }

    @Override
    public void closeDialog(AppointmentResponse appointmentResponse) {
        com.saveAppointment(appointmentResponse);
        dismiss();
    }
}
