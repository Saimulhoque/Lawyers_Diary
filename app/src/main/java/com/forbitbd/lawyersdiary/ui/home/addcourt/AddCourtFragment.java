package com.forbitbd.lawyersdiary.ui.home.addcourt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Court;
import com.forbitbd.lawyersdiary.ui.main.Communicator;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddCourtFragment extends DialogFragment implements View.OnClickListener,AddCourtContract.View {

    private AddCourtPresenter mPresenter;
    private TextInputLayout tiCourtName, tiCityName;
    private TextInputEditText etCourtName;
    private AutoCompleteTextView etCityName;

    private Button btnSave;
    private ImageView icClose;

    private Communicator communicator;


    public AddCourtFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new AddCourtPresenter(this);
        communicator = (Communicator) getActivity();
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
        View view = inflater.inflate(R.layout.fragment_add_court, null);

        initView(view);
        AlertDialog alertDialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Dialog_Alert).create();
        alertDialog.setView(view);
        return alertDialog;
    }

    private void initView(View view) {
        tiCourtName = view.findViewById(R.id.ti_courtname);
        tiCityName = view.findViewById(R.id.ti_court_city);

        etCourtName = view.findViewById(R.id.court_name);
        etCityName = view.findViewById(R.id.court_city);

        String[] city = getResources().getStringArray(R.array.city_names);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, city);
        etCityName.setText(arrayAdapter.getItem(0).toString(), false);
        etCityName.setAdapter(arrayAdapter);

        icClose = view.findViewById(R.id.ic_close);
        btnSave = view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        icClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ic_close){
            dismiss();
        }else if (id == R.id.btn_save){
            String name = etCourtName.getText().toString().trim();
            String city = etCityName.getText().toString().trim();

            Court court = new Court();
            court.setName(name);
            court.setCity(city);
            court.setLawyer_id(AppPreference.getInstance(getContext()).getLawyer().get_id());

            boolean valid =mPresenter.validate(court);

            if(!valid){
                return;
            }
            mPresenter.saveCourtName(court);

        }
    }

    @Override
    public void clearError() {
        tiCourtName.setErrorEnabled(false);
    }

    @Override
    public void setError(int fieldId, String message) {
        if(fieldId==1){
            tiCourtName.setError(message);
            etCourtName.requestFocus();
        }
    }

    @Override
    public void closeDialog(Court body) {
        communicator.getDashboard().getCourts().add(body);
        dismiss();
    }
}
