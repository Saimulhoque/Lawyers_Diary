package com.forbitbd.lawyersdiary.ui.editprofile;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.Lawyer;

public class EditProfilePresenter implements EditProfileContract.Presenter{

    private EditProfileContract.View mView;

    public EditProfilePresenter(EditProfileContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void updateLawyer(Lawyer lawyer) {
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);

    }
}
