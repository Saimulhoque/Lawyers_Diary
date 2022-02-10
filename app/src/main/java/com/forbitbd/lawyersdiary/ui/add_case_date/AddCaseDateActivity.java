package com.forbitbd.lawyersdiary.ui.add_case_date;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.CaseDate;
import com.forbitbd.lawyersdiary.model.CaseType;
import com.forbitbd.lawyersdiary.model.Court;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.ui.addcase.Comm;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.forbitbd.lawyersdiary.utils.Constant;
import com.forbitbd.lawyersdiary.utils.MyUtil;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AddCaseDateActivity extends BaseActivity {

    private TextInputLayout tiCase, tiNextDate, tiJudgeName, tiCourt, tiDocuments;
    private AutoCompleteTextView etCase, etCourt;
    private TextInputEditText etNextDate, etJudgeName, etDocuments;
    private Button btnSave;
    private Dashboard dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case_date);

        dashboard = (Dashboard) getIntent().getSerializableExtra(Constant.DASHBOARD);

        tiCase = findViewById(R.id.ti_case);
        tiNextDate = findViewById(R.id.ti_next_date);
        tiJudgeName = findViewById(R.id.ti_judge_name);
        tiCourt = findViewById(R.id.ti_court);
        tiDocuments = findViewById(R.id.ti_document_requires);

        etCase = findViewById(R.id.et_case);
        etCourt = findViewById(R.id.et_court);

        etNextDate = findViewById(R.id.et_next_date);
        etJudgeName = findViewById(R.id.et_judge_name);
        etDocuments = findViewById(R.id.et_document_requires);

        etNextDate.setText(MyUtil.dateToStr(new Date()));


        etCase.setAdapter(new ArrayAdapter<Case>(this, android.R.layout.simple_expandable_list_item_1,dashboard.getCases()));
        etCourt.setAdapter(new ArrayAdapter<Court>(this, android.R.layout.simple_expandable_list_item_1,dashboard.getCourts()));

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        long today = MaterialDatePicker.todayInUtcMilliseconds();

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT CASE STARTING DATE");
        builder.setSelection(today);
        MaterialDatePicker materialDatePicker = builder.build();

        etNextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),"JJJJJJJ");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                etNextDate.setText(materialDatePicker.getHeaderText());
            }
        });

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String caseName = etCase.getText().toString().trim();
                String nextDate = etNextDate.getText().toString().trim();
                String judgeName = etJudgeName.getText().toString().trim();
                String courtName = etCourt.getText().toString().trim();
                String documentsRequired = etDocuments.getText().toString().trim();

                CaseDate caseDate = new CaseDate();
                caseDate.setCase_id(getCaseObjectId(caseName));
                caseDate.setJudge_name(judgeName);
                caseDate.setCourt(getCourtObjectId(courtName));
                caseDate.setDocuments_required(documentsRequired);

                try {
                    caseDate.setNext_date(MyUtil.strToDate(nextDate));
                } catch (ParseException e) {
                    caseDate.setNext_date(null);
                    e.printStackTrace();
                }
            }
        });
    }

    private String getCourtObjectId(String courtName) {
        for(Court x: dashboard.getCourts()){
            if(x.getName().equals(courtName)){
                return x.get_id();
            }
        }
        return null;
    }

    public String getCaseObjectId(String caseName) {
        for(Case x: dashboard.getCases()){
            if(x.getCase_title().equals(caseName)){
                return x.get_id();
            }
        }
        return null;
    }
}