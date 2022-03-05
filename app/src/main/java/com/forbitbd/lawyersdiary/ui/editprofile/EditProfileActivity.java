package com.forbitbd.lawyersdiary.ui.editprofile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.model.Lawyer;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.forbitbd.lawyersdiary.utils.Constant;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends BaseActivity implements EditProfileContract.View {

    private EditProfilePresenter mPresenter;
    private TextInputLayout tiName, tiEmail, tiPhone, tiBarName, tiMembershipNo, tiAddress;
    private TextInputEditText etName, etEmail, etPhone, etBarName, etMembershipNo, etAddress;
    private CircleImageView civUserImage;
    private Button btnUpdate;
    private Lawyer lawyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        lawyer = (Lawyer) getIntent().getSerializableExtra(Constant.LAWYER);

        mPresenter = new EditProfilePresenter(this);
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

        etEmail.setText(AppPreference.getInstance(this).getLawyer().getEmail());
        etName.setText(AppPreference.getInstance(this).getLawyer().getName());
        Picasso.get().load(AppPreference.getInstance(this).getLawyer().getImage()).into(civUserImage);

        btnUpdate = findViewById(R.id.update_profile);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String mobile = etPhone.getText().toString().trim();
                String bar_name = etBarName.getText().toString().trim();
                String membership_no = etMembershipNo.getText().toString().trim();
                String chamber_address = etAddress.getText().toString().trim();

                String lawyerId = AppPreference.getInstance(getApplicationContext()).getLawyer().get_id();

                Lawyer lawyer = new Lawyer();
                lawyer.setName(name);
                lawyer.setMobile(mobile);
                lawyer.setAddress(chamber_address);
                lawyer.set_id(lawyerId);

                mPresenter.updateLawyer(lawyer);
            }
        });
    }
}


