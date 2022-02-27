package com.forbitbd.lawyersdiary.ui.addcasefees;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.CaseDate;
import com.forbitbd.lawyersdiary.model.CaseFees;
import com.forbitbd.lawyersdiary.model.Dashboard;
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

public class AddCaseFeesActivity extends BaseActivity implements AddCaseFeesContract.View{

    private TextInputLayout tiCase, tiPaymentDate, tiAmount, tiPaymentType, tiRemarks;
    private TextInputEditText etPaymentDate, etAmount, etRemarks;
    private AutoCompleteTextView etCase, etPaymentType;
    private Button btnSave;
    private Dashboard dashboard;
    private AddCaseFeesPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case_fees);

        setupToolbar(R.id.toolbar);
        mPresenter = new AddCaseFeesPresenter(this);
        dashboard = (Dashboard) getIntent().getSerializableExtra(Constant.DASHBOARD);

        tiCase = findViewById(R.id.ti_case);
        tiPaymentDate = findViewById(R.id.ti_payment_date);
        tiAmount = findViewById(R.id.ti_amount);
        tiPaymentType = findViewById(R.id.ti_payment_type);
        tiRemarks = findViewById(R.id.ti_remarks);

        etCase = findViewById(R.id.et_case);
        etPaymentDate = findViewById(R.id.et_payment_date);
        etAmount = findViewById(R.id.et_amount);
        etPaymentType = findViewById(R.id.et_payment_type);
        etRemarks = findViewById(R.id.et_remarks);

        btnSave = findViewById(R.id.btn_save);

        etPaymentDate.setText(MyUtil.dateToStr(new Date()));

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        long today = MaterialDatePicker.todayInUtcMilliseconds();

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT CASE STARTING DATE");
        builder.setSelection(today);
        MaterialDatePicker materialDatePicker = builder.build();

        etPaymentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),"JJJJJJJ");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                etPaymentDate.setText(materialDatePicker.getHeaderText());
            }
        });

        etCase.setAdapter(new ArrayAdapter<Case>(this, android.R.layout.simple_expandable_list_item_1,dashboard.getCases()));

        String[] payment_type = getResources().getStringArray(R.array.payment_type);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, payment_type);
        etPaymentType.setText(arrayAdapter.getItem(0).toString(), false);
        etPaymentType.setAdapter(arrayAdapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String aCase = etCase.getText().toString().trim();
                String paymentDate = etPaymentDate.getText().toString().trim();
                String amount = etAmount.getText().toString().trim();
                String paymentType = etPaymentType.getText().toString().trim();
                String remarks = etRemarks.getText().toString().trim();

                CaseFees caseFees = new CaseFees();
                caseFees.setCase_id(getCaseObjectId(aCase));
                caseFees.setAmount(amount);
                caseFees.setPayment_type(paymentType);
                caseFees.setRemarks(remarks);

                try {
                    caseFees.setPayment_date(MyUtil.strToDate(paymentDate));
                } catch (ParseException e) {
                    caseFees.setPayment_date(null);
                    e.printStackTrace();
                }

                boolean valid =mPresenter.validate(caseFees);

                if(!valid){
                    return;
                }

                mPresenter.saveCaseFees(caseFees);

            }
        });
    }

    public String getCaseObjectId(String aCase) {
        for(Case x: dashboard.getCases()){
            if(x.getCase_title().equals(aCase)){
                return x.get_id();
            }
        }
        return null;
    }

    @Override
    public void clearError() {
        tiCase.setErrorEnabled(false);
        tiPaymentDate.setErrorEnabled(false);
        tiAmount.setErrorEnabled(false);
        tiPaymentType.setErrorEnabled(false);
    }

    @Override
    public void setError(int fieldId, String message) {
        if(fieldId==1){
            tiCase.setError(message);
            etCase.requestFocus();
        }else if (fieldId ==2){
            tiPaymentDate.setError(message);
            etPaymentDate.requestFocus();
        }else if (fieldId ==3){
            tiAmount.setError(message);
            etAmount.requestFocus();
        }else if (fieldId ==4){
            tiPaymentType.setError(message);
            etPaymentType.requestFocus();
        }
    }
}