package com.forbitbd.lawyersdiary.ui.addappointment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Appointment;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.Client;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.ui.addcase.Comm;
import com.forbitbd.lawyersdiary.ui.main.Communicator;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.forbitbd.lawyersdiary.utils.Constant;
import com.forbitbd.lawyersdiary.utils.MyUtil;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AddAppointmentFragment extends DialogFragment implements AddAppointmentContract.View {

    private TextInputLayout tiClient, tiAppointmentDate, tiPurpose, tiRemarks;
    private TextInputEditText etPurpose, etAppointmentDate, etRemarks;
    private AutoCompleteTextView etClient;
    private Button btnSave;
    private Dashboard dashboard;
    private AddAppointmentPresenter mPresenter;
    private Communicator communicator;

    public AddAppointmentFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        tiClient = view.findViewById(R.id.ti_client);
        tiAppointmentDate = view.findViewById(R.id.ti_appointment_date);
        tiPurpose = view.findViewById(R.id.ti_appointment_purpose);
        tiRemarks = view.findViewById(R.id.ti_remarks);

        etClient = view.findViewById(R.id.et_client);
        etAppointmentDate = view.findViewById(R.id.et_appointment_date);
        etPurpose = view.findViewById(R.id.et_appointment_purpose);
        etRemarks = view.findViewById(R.id.et_remarks);
        btnSave = view.findViewById(R.id.btn_save);

        etClient.setAdapter(new ArrayAdapter<Client>(getContext(), android.R.layout.simple_expandable_list_item_1, dashboard.getClients()));

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
                String client = etClient.getText().toString().trim();
                String appointment_date = etAppointmentDate.getText().toString().trim();
                String purpose = etPurpose.getText().toString().trim();
                String remarks = etRemarks.getText().toString().trim();

                Appointment appointment = new Appointment();
                appointment.setClient(getClientObjectId(client));
                appointment.setPurpose(purpose);
                appointment.setRemarks(remarks);
                appointment.setLawyer_id(AppPreference.getInstance(getContext()).getLawyer().get_id());

                try {
                    appointment.setAppointment_date((java.sql.Date) MyUtil.strToDate(appointment_date));
                } catch (ParseException e) {
                    appointment.setAppointment_date(null);
                    e.printStackTrace();
                }

                boolean valid = mPresenter.validate(appointment);

                if (!valid) {
                    return;
                }

                mPresenter.saveAppointment(appointment);

            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Dialog_Alert).create();
        alertDialog.setView(view);
        return alertDialog;
    }

    public String getClientObjectId(String client) {
        for (Client x : dashboard.getClients()) {
            if (x.get_id().equals(client)) {
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
    }

    @Override
    public void setError(int fieldId, String message) {
        if (fieldId == 1) {
            tiClient.setError(message);
            etClient.requestFocus();
        } else if (fieldId == 2) {
            tiAppointmentDate.setError(message);
            etAppointmentDate.requestFocus();
        }
        if (fieldId == 3) {
            tiPurpose.setError(message);
            etPurpose.requestFocus();
        }
    }

    @Override
    public void addAppointment(Appointment appointment) {
        dismiss();
    }
}
