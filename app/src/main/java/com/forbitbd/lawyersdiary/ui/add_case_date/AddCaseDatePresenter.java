package com.forbitbd.lawyersdiary.ui.add_case_date;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.CaseDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCaseDatePresenter implements AddCaseDateContract.Presenter{

    private AddCaseDateContract.View mView;

    public AddCaseDatePresenter(AddCaseDateContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void saveCaseDate(CaseDate caseDate) {
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
        apiClient.saveCaseDate(caseDate).enqueue(new Callback<CaseDate>() {
            @Override
            public void onResponse(Call<CaseDate> call, Response<CaseDate> response) {
                if (response.isSuccessful()){
                    mView.addCaseDate(response.body());
                }
            }

            @Override
            public void onFailure(Call<CaseDate> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean validate(CaseDate caseDate) {
        mView.clearError();
        if(caseDate.getCase_id().equals("")){
            mView.setError(1,"Empty Field Not Allowed");
            return false;
        }else if(caseDate.getNext_date().equals("")){
            mView.setError(2,"Empty Field Not Allowed");
            return false;
        }else if(caseDate.getCourt().equals("")){
            mView.setError(3,"Empty Field Not Allowed");
            return false;
        }
        return true;
    }
}
