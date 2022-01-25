package com.forbitbd.lawyersdiary.ui.addcase;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.ui.main.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class SecondFragment extends Fragment implements Step,BlockingStep {

    private TextInputLayout tiParty, tiOppPartyName, tiOppAdvocateName,
            tiOppAdvocatePhone, tiEvidence, tiCaseHearingDate, tiCaseFees, tiRemarks;
    private TextInputEditText etParty, etOppPartyName, etOppAdvocateName,
            etOppAdvocatePhone, etEvidence, etCaseHearingDate, etCaseFees, etRemarks;

    public SecondFragment() {
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
        View view = inflater.inflate(R.layout.fragment_second, container, false);

//        tiParty = view.findViewById(R.id.ti_com_def);
//        tiOppPartyName = view.findViewById(R.id.ti_opp_party_name);
//        tiOppAdvocateName = view.findViewById(R.id.ti_opp_adv);
//        tiOppAdvocatePhone = view.findViewById(R.id.ti_opp_adv_phone);
//        tiEvidence = view.findViewById(R.id.ti_evidence);
//        tiCaseHearingDate = view.findViewById(R.id.ti_hearing_date);
//        tiCaseFees = view.findViewById(R.id.ti_case_fees);
//        tiRemarks = view.findViewById(R.id.ti_remarks);
//
//        etParty = view.findViewById(R.id.com_def);
//        etOppPartyName = view.findViewById(R.id.opp_party_name);
//        etOppAdvocateName = view.findViewById(R.id.opp_advocate);
//        etOppAdvocatePhone = view.findViewById(R.id.opp_advocate_phone);
//        etEvidence = view.findViewById(R.id.case_evidence);
//        etCaseHearingDate = view.findViewById(R.id.hearing_date);
//        etCaseFees = view.findViewById(R.id.case_fees);
//        etRemarks = view.findViewById(R.id.case_remarks);

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

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        getActivity().onBackPressed();
    }
}