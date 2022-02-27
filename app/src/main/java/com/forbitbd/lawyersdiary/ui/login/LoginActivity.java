package com.forbitbd.lawyersdiary.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.Lawyer;
import com.forbitbd.lawyersdiary.ui.main.MainActivity;
import com.forbitbd.lawyersdiary.ui.signup.SignupActivity;
import com.forbitbd.lawyersdiary.utils.AppPreference;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        GoogleSignInResult r = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                        mPresenter.startAutentication(r);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = new LoginPresenter(this,AppPreference.getInstance(this));

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
                    if(user!=null){

                    }
                }
            }
        });
    }

    private void googleSignIn() {
        Intent signInIntent = getGoogleApiClient().getSignInIntent();
        someActivityResultLauncher.launch(signInIntent);
    }

    @Override
    public void startMainActivity(Lawyer lawyer) {
        AppPreference.getInstance(this).setLawyer(lawyer);
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
