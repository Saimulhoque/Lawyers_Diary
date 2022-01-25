package com.forbitbd.lawyersdiary.ui.evidence;

import android.os.Bundle;
import android.view.View;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class EvidenceActivity extends BaseActivity implements EvidenceContract.View{

    private EvidencePresenter mPresenter;

    private ExtendedFloatingActionButton btnEFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evidence);
        mPresenter = new EvidencePresenter(this);
        setupToolbar(R.id.toolbar);
        btnEFAB = findViewById(R.id.add_evidence);
        btnEFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}