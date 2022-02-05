package com.forbitbd.lawyersdiary.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.ui.login.LoginActivity;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.forbitbd.lawyersdiary.utils.PrebaseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends PrebaseActivity implements View.OnClickListener, SignupContract.View {

    private TextInputLayout tiEmail, tiPassword;
    private TextInputEditText etEmail,etPassword;
    private Button btnSignup;
    private TextView txLogin;
    private RadioButton rblawyer, rbclient;
    private SignupPresenter mPresenter;
    FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mPresenter = new SignupPresenter(this);
        initView();
    }

    private void initView() {
        tiEmail = findViewById(R.id.ti_email);
        tiPassword = findViewById(R.id.ti_password);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSignup = findViewById(R.id.btn_signup);
        txLogin = findViewById(R.id.tx_login);
        rblawyer = findViewById(R.id.rb_lawyer);
        rbclient = findViewById(R.id.rb_client);

        btnSignup.setOnClickListener(this);
        txLogin.setOnClickListener(this);

        rblawyer.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.sign_up){
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            boolean valid = mPresenter.validate(email,password);

            if(!valid){
                return;
            }

            mPresenter.signUp(email, password);
        }else if (id == R.id.tx_login){
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        }
    }

    public void showSignupSuceesDialog() {
        Toast toast = Toast.makeText(this, "We send a verification mail to your Email. Please verify and then Login", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void hideDialog() {
//        hideProgressDialog();
    }

    @Override
    public void showToast(String failed_to_send_varification_email, int i) {

    }

    @Override
    public void clearPreError() {
        tiEmail.setErrorEnabled(false);
        tiPassword.setErrorEnabled(false);
    }

    @Override
    public void showErrorMessage(String message, int fieldId) {
        switch (fieldId){
            case 1:
                etEmail.requestFocus();
                tiEmail.setError(message);
                break;

            case 2:
                etPassword.requestFocus();
                tiPassword.setError(message);
                break;
        }
    }

    @Override
    public void showDialog() {
        showSignupSuceesDialog();
    }


}