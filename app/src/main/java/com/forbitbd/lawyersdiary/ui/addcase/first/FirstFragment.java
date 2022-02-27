package com.forbitbd.lawyersdiary.ui.addcase.first;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.CaseType;
import com.forbitbd.lawyersdiary.model.Client;
import com.forbitbd.lawyersdiary.model.Court;
import com.forbitbd.lawyersdiary.ui.addcase.Comm;
import com.forbitbd.lawyersdiary.utils.MyUtil;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FirstFragment extends Fragment implements Step, BlockingStep, FirstFragmentContract.View {

    private FirstFragmentPresenter mPresenter;
    private TextInputLayout tiCaseTitle, tiCaseNumber, tiCaseFileNumber, tiCaseType,
            tiCaseRegDate, tiCourtName, tiCourtCity, tiClientName, tiParty;

    private TextInputEditText etCaseTitle, etCaseNumber, etCaseFileNumber,
            etCaseRegDate;

    private AutoCompleteTextView etCaseType, etCourtName,etCourtCity, etClientName, etParty;

    private Comm comm;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comm = (Comm) getActivity();
        mPresenter = new FirstFragmentPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        tiCaseTitle = view.findViewById(R.id.ti_casetitle);
        tiCaseNumber = view.findViewById(R.id.ti_casenumber);
        tiCaseFileNumber = view.findViewById(R.id.ti_casefileno);
        tiCaseType = view.findViewById(R.id.ti_casetype);
        tiCaseRegDate = view.findViewById(R.id.ti_casestartdate);
        tiCourtName = view.findViewById(R.id.ti_courtname);
        tiCourtCity = view.findViewById(R.id.ti_courtcity);
        tiParty = view.findViewById(R.id.ti_com_def);
        tiClientName = view.findViewById(R.id.ti_clientname);

        etCaseTitle = view.findViewById(R.id.case_title);
        etCaseNumber = view.findViewById(R.id.case_number);
        etCaseFileNumber = view.findViewById(R.id.case_file_no);
        etCaseType = view.findViewById(R.id.case_type);
        etCaseRegDate = view.findViewById(R.id.case_start_date);
        etCourtName = view.findViewById(R.id.court_name);
        etCourtCity = view.findViewById(R.id.court_city);
        etParty = view.findViewById(R.id.com_def);
        etClientName = view.findViewById(R.id.client_name);

        etCaseType.setAdapter(new ArrayAdapter<CaseType>(getContext(), android.R.layout.simple_expandable_list_item_1,comm.getDashBoard().getCaseTypes()));
        etCourtName.setAdapter(new ArrayAdapter<Court>(getContext(), android.R.layout.simple_expandable_list_item_1,comm.getDashBoard().getCourts()));
        etClientName.setAdapter(new ArrayAdapter<Client>(getContext(), android.R.layout.simple_expandable_list_item_1,comm.getDashBoard().getClients()));

        String[] party = getResources().getStringArray(R.array.party);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, party);
        etParty.setText(arrayAdapter.getItem(0).toString(), false);
        etParty.setAdapter(arrayAdapter);

        String[] city = getResources().getStringArray(R.array.city_names);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, city);
        etCourtCity.setText(arrayAdapter2.getItem(0).toString(), false);
        etCourtCity.setAdapter(arrayAdapter2);

        etCaseRegDate.setText(MyUtil.dateToStr(new Date()));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        long today = MaterialDatePicker.todayInUtcMilliseconds();

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT CASE STARTING DATE");
        builder.setSelection(today);
        MaterialDatePicker materialDatePicker = builder.build();

        etCaseRegDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getChildFragmentManager(),"JJJJJJJ");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                etCaseRegDate.setText(materialDatePicker.getHeaderText());
            }
        });

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

        String caseTitle = etCaseTitle.getText().toString().trim();
        String caseNumber = etCaseNumber.getText().toString().trim();
        String caseFileNumber = etCaseFileNumber.getText().toString().trim();
        String caseType = etCaseType.getText().toString().trim();

        String caseRegDate = etCaseRegDate.getText().toString().trim();
        String caseCourtName = etCourtName.getText().toString().trim();
        String courtCityName = etCourtCity.getText().toString().trim();
        String clientName = etClientName.getText().toString().trim();
        String partyName = etParty.getText().toString().trim();

        Case ca_se = new Case();
        ca_se.setCase_title(caseTitle);
        ca_se.setCase_number(caseNumber);
        ca_se.setFile_number(caseFileNumber);
        ca_se.setCase_type(comm.getCaseTypeObjectId(caseType));
        ca_se.setCourt(comm.getCourtObjectId(caseCourtName));
        ca_se.setClient(comm.getClientObjectId(clientName));
        ca_se.setComplainant_Defendant(partyName);


        try {
            ca_se.setCase_reg_date(MyUtil.strToDate(caseRegDate));
        } catch (ParseException e) {
            ca_se.setCase_reg_date(null);
            e.printStackTrace();
        }

        ca_se.setCity(courtCityName);

        boolean valid =mPresenter.validate(ca_se);

        if(!valid){
            return;
        }

        mPresenter.saveCase(ca_se);
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }

    @Override
    public void clearError() {
        tiCaseTitle.setErrorEnabled(false);
        tiCaseNumber.setErrorEnabled(false);
        tiCaseFileNumber.setErrorEnabled(false);
        tiCaseType.setErrorEnabled(false);
        tiCourtName.setErrorEnabled(false);
        tiClientName.setErrorEnabled(false);
        tiCaseRegDate.setErrorEnabled(false);
    }

    @Override
    public void setError(int fieldId, String message) {
        if(fieldId==1){
            tiCaseTitle.setError(message);
            etCaseTitle.requestFocus();
        }else if (fieldId ==2){
            tiCaseNumber.setError(message);
            etCaseNumber.requestFocus();
        }else if (fieldId ==3){
            tiCaseFileNumber.setError(message);
            etCaseFileNumber.requestFocus();
        }else if (fieldId ==4){
            tiCaseType.setError(message);
            etCaseType.requestFocus();
        }else if (fieldId ==5){
            tiCourtName.setError(message);
            etCourtName.requestFocus();
        }else if (fieldId ==6){
            tiClientName.setError(message);
            etClientName.requestFocus();
        }else if (fieldId ==7){
            tiCaseRegDate.setError(message);
            etCaseRegDate.requestFocus();
        }
    }

    @Override
    public void saveCaseInActivity(Case ca_se) {
        comm.saveCase(ca_se);

    }
}
