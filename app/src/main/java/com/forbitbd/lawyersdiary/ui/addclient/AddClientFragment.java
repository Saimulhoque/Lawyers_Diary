package com.forbitbd.lawyersdiary.ui.addclient;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Client;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

public class AddClientFragment extends DialogFragment implements View.OnClickListener,AddClientContract.View {

    private AddClientPresenter mPresenter;
    private ImageView ivclose;
    private TextInputLayout tiName, tiPhone1, tiPhone2, tiAddress, tiEmail, tiBirthDate;
    private TextInputEditText etName, etPhone1,etPhone2, etAddress, etEmail, etBirthDate;
    private Button btnSave;

    public AddClientFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new AddClientPresenter(this);
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

        initView(view);

        AlertDialog alertDialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Dialog_Alert).create();
        alertDialog.setView(view);
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

            Client client = new Client();
            client.setName(name);
            client.setPhone_one(phone1);
            client.setPhone_two(phone2);
            client.setAddress(address);
            client.setEmail(email);
            client.setDate_of_birth(birthdate);

            Date d = new Date();
            CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
            client.setReg_date(String.valueOf(s));
            client.setLawyer_id(AppPreference.getInstance(getContext()).getLawyer().get_id());


            boolean valid =mPresenter.validate(client);

            if(!valid){
                return;
            }
            Log.d("KKKKKK", "onResponse: Successful!");
            mPresenter.saveClient(client);

        }
    }

    @Override
    public void clearError() {
        tiName.setErrorEnabled(false);
        tiPhone1.setErrorEnabled(false);
        tiPhone2.setErrorEnabled(false);
        tiAddress.setErrorEnabled(false);
        tiEmail.setErrorEnabled(false);
        tiBirthDate.setErrorEnabled(false);
    }

    @Override
    public void setError(int fieldId, String message) {
        if(fieldId==1){
            tiName.setError(message);
            etName.requestFocus();
        }else if (fieldId == 2){
            tiPhone1.setError(message);
            etPhone1.requestFocus();
        }else if (fieldId==3){
            tiPhone2.setError(message);
            etPhone2.requestFocus();
        }else if (fieldId==4){
            tiAddress.setError(message);
            etAddress.requestFocus();
        }else if (fieldId==5){
            tiBirthDate.setError(message);
            etBirthDate.requestFocus();
        }
    }

    @Override
    public void closeDialog(Client client) {
        dismiss();
    }
}
