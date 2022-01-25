package com.forbitbd.lawyersdiary.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.ui.main.MainActivity;
import com.forbitbd.lawyersdiary.ui.signup.SignupActivity;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginContract.View {

    private TextInputLayout tiEmail, tiPassword;
    private TextInputEditText etEmail,etPassword;
    private Button btnLogin;
    private SignInButton btnSignin;
    private TextView txReset, txSignup;
    private RadioButton rblawyer, rbclient;
    private LoginPresenter mPresenter;
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter(this);
        initView();

    }

    private void initView() {
        tiEmail = findViewById(R.id.ti_email);
        tiPassword = findViewById(R.id.ti_password);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        btnLogin = findViewById(R.id.btn_login);
        btnSignin = findViewById(R.id.btn_google);
        txReset = findViewById(R.id.reset_password);
        txSignup = findViewById(R.id.sign_up);
        rblawyer = findViewById(R.id.rb_lawyer);
        rbclient = findViewById(R.id.rb_client);

        btnLogin.setOnClickListener(this);
        btnSignin.setOnClickListener(this);
        txReset.setOnClickListener(this);
        txSignup.setOnClickListener(this);

        rblawyer.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_login){
            loginUser();
        }else if(id == R.id.btn_google){
            googleSignIn();
        }else if(id == R.id.reset_password){

        }else if(id == R.id.sign_up){
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        }
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    final FirebaseUser user = mAuth.getCurrentUser();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

            }
        });
    }

    private void googleSignIn() {
        Intent signInIntent = getGoogleApiClient().getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case RC_SIGN_IN:
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                mPresenter.startAutentication(result);
                break;
        }
    }

    @Override
    public void startMainActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}