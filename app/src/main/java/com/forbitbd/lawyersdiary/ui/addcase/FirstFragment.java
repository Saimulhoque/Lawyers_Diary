package com.forbitbd.lawyersdiary.ui.addcase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.forbitbd.lawyersdiary.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

public class FirstFragment extends Fragment implements Step {

    private TextInputLayout tiCaseTitle, tiCaseNumber, tiCaseFileNumber,
            tiCaseType, tiCaseStartingDate, tiCourtName, tiJudgeName, tiClientName;
    private TextInputEditText etCaseTitle, etCaseNumber, etCaseFileNumber,
            etCaseType, etCaseStartingDate, etCourtName, etJudgeName, etClientName;


    public FirstFragment() {
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
        View view = inflater.inflate(R.layout.fragment_first, container, false);

//        tiCaseTitle = view.findViewById(R.id.ti_casetitle);
//        tiCaseNumber = view.findViewById(R.id.ti_casenumber);
//        tiCaseFileNumber = view.findViewById(R.id.ti_casefileno);
//        tiCaseType = view.findViewById(R.id.ti_casetype);
//        tiCaseStartingDate = view.findViewById(R.id.ti_casestartdate);
//        tiCourtName = view.findViewById(R.id.ti_courtname);
//        tiJudgeName = view.findViewById(R.id.ti_judgename);
//        tiClientName = view.findViewById(R.id.ti_clientname);
//
//        etCaseTitle = view.findViewById(R.id.case_title);
//        etCaseNumber = view.findViewById(R.id.case_number);
//        etCaseFileNumber = view.findViewById(R.id.case_file_no);
//        etCaseType = view.findViewById(R.id.case_type);
//        etCaseStartingDate = view.findViewById(R.id.case_start_date);
//        etCourtName = view.findViewById(R.id.court_name);
//        etJudgeName = view.findViewById(R.id.judge_name);
//        etClientName = view.findViewById(R.id.client_name);


        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }


}