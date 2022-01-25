package com.forbitbd.lawyersdiary.ui.aboutus;

import android.os.Bundle;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.utils.BaseActivity;

public class AboutUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setupToolbar(R.id.toolbar);

    }
}