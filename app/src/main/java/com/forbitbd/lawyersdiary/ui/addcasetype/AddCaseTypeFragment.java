package com.forbitbd.lawyersdiary.ui.addcasetype;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
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
import com.forbitbd.lawyersdiary.model.CaseType;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddCaseTypeFragment extends DialogFragment implements View.OnClickListener,AddCaseTypeContract.View {

    private AddCaseTypePresenter mPresenter;
    private ImageView ivclose;
    private TextInputLayout tiCaseType;
    private TextInputEditText etCaseType;
    private Button btnSave;

    public AddCaseTypeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new AddCaseTypePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_case_type, container, false);

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_add_case_type, null);

        ivclose = view.findViewById(R.id.ic_close);
        tiCaseType = view.findViewById(R.id.ti_casetype);
        etCaseType = view.findViewById(R.id.case_type);
        btnSave = view.findViewById(R.id.btn_save);

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
            String casetypeName = etCaseType.getText().toString().trim();

            CaseType caseType = new CaseType();
            caseType.setCase_type(casetypeName);
            caseType.setLawyer_id(AppPreference.getInstance(getContext()).getLawyer().get_id());

            boolean valid =mPresenter.validate(caseType);

            if(!valid){
                return;
            }
            mPresenter.saveCaseType(caseType);
        }
    }

    @Override
    public void clearError() {
        tiCaseType.setErrorEnabled(false);
    }

    @Override
    public void setError(int fieldId, String message) {
        if(fieldId==1){
            tiCaseType.setError(message);
            etCaseType.requestFocus();
        }
    }

    @Override
    public void closeDialog(CaseType caseType) {
        dismiss();
    }
}