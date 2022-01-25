package com.forbitbd.lawyersdiary.ui.editprofile;

public class EditProfilePresenter implements EditProfileContract.Presenter{

    private EditProfileContract.View mView;

    public EditProfilePresenter(EditProfileContract.View mView) {
        this.mView = mView;
    }
}
