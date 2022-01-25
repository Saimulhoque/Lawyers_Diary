package com.forbitbd.lawyersdiary.ui.addcourt;

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


public class AddCourtFragment extends DialogFragment implements View.OnClickListener {

    private ImageView ivclose;

    private TextInputLayout tiCourtName, tiCityName;
    private TextInputEditText etCourtName, etCityName;
    private Button btnSave;

    public AddCourtFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_court, container, false);

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_add_court,null);

        initView(view);

        AlertDialog alertDialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Dialog_Alert).create();
        alertDialog.setView(view);
        return alertDialog;
    }

    private void initView(View view) {
        ivclose = view.findViewById(R.id.ic_close);

        tiCourtName = view.findViewById(R.id.ti_courtname);
        tiCityName = view.findViewById(R.id.ti_court_city);
//        etCourtName = view.findViewById(R.id.court_name);
//        etCityName = view.findViewById(R.id.court_city);

        btnSave = view.findViewById(R.id.btn_save);

//        ivclose.setOnClickListener(this);
//        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        int id  = v.getId();
//        if (id == R.id.ic_close){
//            dismiss();
//
//        }else if (id == R.id.btn_save){
//            String courtName = etCourtName.getText().toString().trim();
//
//        }
    }
}