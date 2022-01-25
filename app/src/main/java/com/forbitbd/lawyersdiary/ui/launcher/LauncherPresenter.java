package com.forbitbd.lawyersdiary.ui.launcher;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LauncherPresenter implements LauncherContract.Presenter{

    private LauncherContract.View mView;
    private FirebaseUser mCurrentUser;

    public LauncherPresenter(LauncherContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void checkdealer() {
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mCurrentUser == null) {
            mView.Startlogin();
        } else {

            mView.StartMain();
        }
    }
}
