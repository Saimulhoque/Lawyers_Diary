package com.forbitbd.lawyersdiary.ui.addcase.second;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.ui.addcase.Comm;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class SecondFragment extends Fragment implements Step,BlockingStep, SecondFragmentContract.View {

    private SecondFragmentPresenter mPresenter;
    private TextInputLayout tiOppPartyName, tiOppAdvocateName, tiOppAdvocatePhone, tiCaseFees, tiRemarks;
    private TextInputEditText etOppPartyName, etOppAdvocateName, etOppAdvocatePhone,etCaseFees, etRemarks;
    private Comm comm;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comm = (Comm) getActivity();
        mPresenter = new SecondFragmentPresenter(this);
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

        Case aCase = comm.getCase();

        aCase.setOpposition_party_name(oppPartyName);
        aCase.setOpposition_lawyer_name(oppAdvocateName);
        aCase.setOpposition_lawyer_phone(oppAdvocatePhone);

        try {
            aCase.setCase_fees(Double.parseDouble(caseFees));
        }catch (Exception e){
            aCase.setCase_fees(0);
        }

        aCase.setRemarks(remarks);

        boolean valid = mPresenter.validate(aCase);

        if(!valid){
            return;
        }

        mPresenter.saveCaseToServer(aCase);

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    @Override
    public void clearError() {
        tiOppPartyName.setErrorEnabled(false);
        tiOppAdvocateName.setErrorEnabled(false);
        tiOppAdvocatePhone.setErrorEnabled(false);
        tiCaseFees.setErrorEnabled(false);
    }

    @Override
    public void finishActivity(Case aCase) {
        comm.finishActivity(aCase);
    }

    @Override
    public void setError(int fieldId, String message) {
        if(fieldId==1){
            tiOppPartyName.setError(message);
            etOppPartyName.requestFocus();
        }else if (fieldId ==2){
            tiOppAdvocateName.setError(message);
            etOppAdvocateName.requestFocus();
        }else if (fieldId ==3){
            tiOppAdvocatePhone.setError(message);
            etOppAdvocatePhone.requestFocus();
        }else if (fieldId ==4){
            tiCaseFees.setError(message);
            etCaseFees.requestFocus();
        }
    }
}
