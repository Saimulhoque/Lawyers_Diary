package com.forbitbd.lawyersdiary.ui.laws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.utils.BaseActivity;

public class LawActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);

        setupToolbar(R.id.toolbar);

    }
}