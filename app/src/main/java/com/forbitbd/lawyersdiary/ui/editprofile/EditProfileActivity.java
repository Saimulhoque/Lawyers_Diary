package com.forbitbd.lawyersdiary.ui.editprofile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends BaseActivity implements EditProfileContract.View{

    private EditProfilePresenter mPresenter;

    private TextInputLayout tiName, tiEmail, tiPhone, tiBarName, tiMembershipNo, tiAddress;
    private TextInputEditText etName, etEmail, etPhone, etBarName, etMembershipNo, etAddress;
    private CircleImageView civUserImage;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setupToolbar(R.id.toolbar);
        initView();

    }

    private void initView() {
        tiName = findViewById(R.id.ti_username);
        tiEmail = findViewById(R.id.ti_useremail);
        tiPhone = findViewById(R.id.ti_userphone);
        tiBarName = findViewById(R.id.ti_barname);
        tiMembershipNo = findViewById(R.id.ti_mem_no);
        tiAddress = findViewById(R.id.ti_chamber_address);

        etName = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_useremail);
        etPhone = findViewById(R.id.et_userphone);
        etBarName = findViewById(R.id.et_barname);
        etMembershipNo = findViewById(R.id.et_mem_no);
        etAddress = findViewById(R.id.et_chamber_address);

        civUserImage = findViewById(R.id.user_image);
        btnUpdate = findViewById(R.id.update_profile);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}