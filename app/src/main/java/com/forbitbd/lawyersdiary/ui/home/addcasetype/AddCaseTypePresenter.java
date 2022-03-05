package com.forbitbd.lawyersdiary.ui.home.addcasetype;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.CaseType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCaseTypePresenter implements AddCaseTypeContract.Presenter{

    private AddCaseTypeContract.View mView;

    public AddCaseTypePresenter(AddCaseTypeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(CaseType caseType) {
        mView.clearError();
        if(caseType.getCase_type().equals("")){
            mView.setError(1,"Empty Field Not Allowed");
            return false;
        }
        return true;
    }

    @Override
    public void saveCaseType(CaseType caseType) {
        ApiClient client = ServiceGenerator.createService(ApiClient.class);
        client.postCaseType(caseType)
                .enqueue(new Callback<CaseType>() {
                    @Override
                    public void onResponse(Call<CaseType> call, Response<CaseType> response) {
                        if(response.isSuccessful()){
                            mView.closeDialog(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<CaseType> call, Throwable t) {

                    }
                });
    }
}
