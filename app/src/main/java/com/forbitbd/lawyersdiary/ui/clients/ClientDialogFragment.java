package com.forbitbd.lawyersdiary.ui.clients;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.ui.addclient.AddClientPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ClientDialogFragment extends DialogFragment implements View.OnClickListener {

    private AddClientPresenter mPresenter;
    private ImageView ivclose;
    private TextInputLayout tiName, tiPhone1, tiPhone2, tiAddress, tiEmail, tiBirthDate;
    private TextInputEditText etName, etPhone1,etPhone2, etAddress, etEmail, etBirthDate;
    private Button btnSave;

    public ClientDialogFragment() {
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
        View view = inflater.inflate(R.layout.fragment_client_dialog, container, false);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_client_dialog, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Dialog_Alert).create();
        alertDialog.setView(view);

        initView(view);
        return alertDialog;
    }

    private void initView(View view) {
        ivclose = view.findViewById(R.id.ic_close);

        tiName = view.findViewById(R.id.ti_clientname);
        tiPhone1 = view.findViewById(R.id.ti_clientphone1);
        tiPhone2 = view.findViewById(R.id.ti_clientphone2);
        tiAddress = view.findViewById(R.id.ti_clientaddress);
        tiEmail = view.findViewById(R.id.ti_clientemail);
        tiBirthDate = view.findViewById(R.id.ti_clientbd);

        etName = view.findViewById(R.id.client_name);
        etPhone1 = view.findViewById(R.id.client_phone1);
        etPhone2 = view.findViewById(R.id.client_phone2);
        etAddress = view.findViewById(R.id.client_address);
        etEmail = view.findViewById(R.id.client_email);
        etBirthDate = view.findViewById(R.id.client_birthdate);

        btnSave = view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        ivclose.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ic_close){
            dismiss();

        }
    }
}
