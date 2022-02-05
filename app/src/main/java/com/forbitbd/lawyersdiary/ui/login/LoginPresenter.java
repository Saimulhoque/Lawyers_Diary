package com.forbitbd.lawyersdiary.ui.login;

import androidx.annotation.NonNull;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.Lawyer;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View mView;
    private AppPreference appPreference;

    public LoginPresenter(LoginContract.View mView, AppPreference appPreference) {
        this.mView = mView;
        this.appPreference = appPreference;
    }

    @Override
    public void startAutentication(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {

                        registerToDatabase(user);

                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void registerToDatabase(FirebaseUser user) {
        Lawyer lawyer = new Lawyer();
        if (user.getEmail() != null ){
            lawyer.setEmail(user.getEmail());
        }else if (user.getDisplayName() != null ){
            lawyer.setName(user.getDisplayName());
        }else if (user.getPhoneNumber() != null ){
            lawyer.setMobile(user.getPhoneNumber());
        }else if (user.getPhotoUrl() != null ){
            lawyer.setImage(user.getPhotoUrl().toString());
        }

        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
        Call<Lawyer> call = apiClient.register(lawyer);
        call.enqueue(new Callback<Lawyer>() {
            @Override
            public void onResponse(Call<Lawyer> call, Response<Lawyer> response) {
                if(response.isSuccessful()){
                    mView.startMainActivity(response.body());
                }
            }

            @Override
            public void onFailure(Call<Lawyer> call, Throwable t) {
            }
        });
    }
}
