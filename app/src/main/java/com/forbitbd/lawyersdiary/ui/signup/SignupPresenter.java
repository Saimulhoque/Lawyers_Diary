package com.forbitbd.lawyersdiary.ui.signup;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.forbitbd.lawyersdiary.utils.MyUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupPresenter implements SignupContract.Presenter {

    private SignupContract.View mView;
    private FirebaseAuth mAuth;

    public SignupPresenter(SignupContract.View mView) {
        this.mView = mView;
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean validate(String email, String password) {
        mView.clearPreError();
        if (email.equals("")) {
            mView.showErrorMessage("Empty Value Not Allowed", 1);
            return false;
        }

        if (!MyUtil.isValidEmail(email)) {
            mView.showErrorMessage("Email is not Valid", 1);
            return false;
        }

        if (password.equals("")) {
            mView.showErrorMessage("Empty Value Not Allowed", 2);
            return false;
        }

        if (password.length() < 6) {
            mView.showErrorMessage("Password atleast 6 Character Long", 2);
            return false;
        }
        return true;
    }

    @Override
    public void signUp(String email, String password) {
        mView.showDialog();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) mView, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            if (user != null) {
                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            mView.showSignupSuceesDialog();
                                        } else {
                                            mView.hideDialog();
                                            mView.showToast("Failed to Send Varification Email", 3);
                                        }

                                    }
                                });
                            }

                        } else {
                            mView.hideDialog();
                            mView.showToast("Signup Failed", 3);
                        }
                    }
                });
    }
}
