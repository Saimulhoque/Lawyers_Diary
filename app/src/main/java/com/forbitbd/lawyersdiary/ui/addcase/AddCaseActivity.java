package com.forbitbd.lawyersdiary.ui.addcase;

import android.content.Intent;
import android.os.Bundle;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.CaseType;
import com.forbitbd.lawyersdiary.model.Client;
import com.forbitbd.lawyersdiary.model.Court;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.forbitbd.lawyersdiary.utils.Constant;
import com.stepstone.stepper.StepperLayout;

public class AddCaseActivity extends BaseActivity implements Comm {

    private StepperLayout stepperLayout;
    private Dashboard dashboard;

    private Case aCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);

        dashboard = (Dashboard) getIntent().getSerializableExtra(Constant.DASHBOARD);

        setupToolbar(R.id.toolbar);
        stepperLayout = findViewById(R.id.stepper_layout);
        StepperAdapter adapter = new StepperAdapter(getSupportFragmentManager(),this);
        stepperLayout.setAdapter(adapter);

    }

    @Override
    public Dashboard getDashBoard() {
        return dashboard;
    }

    @Override
    public String getCaseTypeObjectId(String caseType){
        for(CaseType x: dashboard.getCaseTypes()){
            if(x.getCase_type().equals(caseType)){
                return x.get_id();
            }
        }
        return null;
    }

    @Override
    public String getCourtObjectId(String courtName) {
        for(Court x: dashboard.getCourts()){
            if(x.getName().equals(courtName)){
                return x.get_id();
            }
        }
        return null;
    }

    @Override
    public String getClientObjectId(String clientName) {
        for(Client x: dashboard.getClients()){
            if(x.getName().equals(clientName)){
                return x.get_id();
            }
        }
        return null;
    }

    @Override
    public void saveCase(Case ca_se) {
        aCase = ca_se;
        aCase.setLawyer_id(AppPreference.getInstance(this).getLawyer().get_id());
    }

    @Override
    public void finishActivity(Case aCase) {
        Intent intent = new Intent();
        intent.putExtra(Constant.CASE,aCase);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public Case getCase() {
        return aCase;
    }
}
