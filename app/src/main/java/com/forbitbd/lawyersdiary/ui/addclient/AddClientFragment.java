package com.forbitbd.lawyersdiary.ui.addclient;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.forbitbd.lawyersdiary.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddClientFragment extends DialogFragment implements View.OnClickListener {

    private ImageView ivclose;
    private TextInputLayout tiName, tiPhone1, tiPhone2, tiAddress, tiEmail, tiBirthDate, tiRegDate;
    private TextInputEditText etName, etPhone1,etPhone2, etAddress, etEmail, etBirthDate, etRegDate;
    private Button btnSave;

    public AddClientFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_client, container, false);

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_add_client,null);

        ivclose = view.findViewById(R.id.ic_close);

        tiName = view.findViewById(R.id.ti_clientname);
        tiPhone1 = view.findViewById(R.id.ti_clientphone1);
        tiPhone2 = view.findViewById(R.id.ti_clientphone2);
        tiAddress = view.findViewById(R.id.ti_clientaddress);
        tiEmail = view.findViewById(R.id.ti_clientemail);
        tiBirthDate = view.findViewById(R.id.ti_clientbd);
        tiRegDate = view.findViewById(R.id.ti_clientregdate);

        etName = view.findViewById(R.id.client_name);
        etPhone1 = view.findViewById(R.id.client_phone1);
        etPhone2 = view.findViewById(R.id.client_phone2);
        etAddress = view.findViewById(R.id.client_address);
        etEmail = view.findViewById(R.id.client_email);
        etBirthDate = view.findViewById(R.id.client_birthdate);
        etRegDate = view.findViewById(R.id.client_reg_date);

        btnSave = view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

        ivclose.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        AlertDialog alertDialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Dialog_Alert).create();
        alertDialog.setView(view);
        return alertDialog;
    }

    @Override
    public void onClick(View v) {
        int id  = v.getId();
        if (id == R.id.ic_close){
            dismiss();

        }else if (id == R.id.btn_save){
            String name = etName.getText().toString().trim();
            String phone1 = etPhone1.getText().toString().trim();
            String phone2 = etPhone2.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String birthdate = etBirthDate.getText().toString().trim();
            String regdate = etRegDate.getText().toString().trim();


        }
    }
}
