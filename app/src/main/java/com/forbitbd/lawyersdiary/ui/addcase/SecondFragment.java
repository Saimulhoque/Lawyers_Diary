package com.forbitbd.lawyersdiary.ui.addcase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.forbitbd.lawyersdiary.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class SecondFragment extends Fragment implements Step,BlockingStep {

    private AutoCompleteTextView etCaseFees;
    private TextInputLayout tiOppPartyName, tiOppAdvocateName, tiOppAdvocatePhone, tiCaseFees, tiRemarks;
    private TextInputEditText etOppPartyName, etOppAdvocateName, etOppAdvocatePhone, etRemarks;

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

        tiOppPartyName = view.findViewById(R.id.ti_opp_party_name);
        tiOppAdvocateName = view.findViewById(R.id.ti_opp_adv);
        tiOppAdvocatePhone = view.findViewById(R.id.ti_opp_adv_phone);
        tiCaseFees = view.findViewById(R.id.ti_case_fees);
        tiRemarks = view.findViewById(R.id.ti_remarks);

        etOppPartyName = view.findViewById(R.id.opp_party_name);
        etOppAdvocateName = view.findViewById(R.id.opp_advocate);
        etOppAdvocatePhone = view.findViewById(R.id.opp_advocate_phone);
        etCaseFees = view.findViewById(R.id.case_fees);
        etRemarks = view.findViewById(R.id.case_remarks);

        String[] party = getResources().getStringArray(R.array.fees);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, party);
        etCaseFees.setText(arrayAdapter.getItem(0).toString(), false);
        etCaseFees.setAdapter(arrayAdapter);

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

        String oppPartyName = etOppPartyName.getText().toString().trim();
        String oppAdvocateName = etOppAdvocateName.getText().toString().trim();
        String oppAdvocatePhone = etOppAdvocatePhone.getText().toString().trim();
        String caseFees = etCaseFees.getText().toString().trim();
        String remarks = etRemarks.getText().toString().trim();

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
