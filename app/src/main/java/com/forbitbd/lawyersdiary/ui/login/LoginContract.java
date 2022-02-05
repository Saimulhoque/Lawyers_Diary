package com.forbitbd.lawyersdiary.ui.login;

import com.forbitbd.lawyersdiary.model.Lawyer;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public interface LoginContract {

    interface Presenter{
        void startAutentication(GoogleSignInResult result);
    }

    interface View{
        void startMainActivity(Lawyer lawyer);
    }
}
