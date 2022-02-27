package com.forbitbd.lawyersdiary.ui.addcasefees;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.CaseFees;
import com.google.android.gms.common.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCaseFeesPresenter implements AddCaseFeesContract.Presenter{

    private AddCaseFeesContract.View mView;

    public AddCaseFeesPresenter(AddCaseFeesContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(CaseFees caseFees) {
        mView.clearError();
        if(caseFees.getCase_id().equals("")){
            mView.setError(1,"Empty Field Not Allowed");
            return false;
        }else if(caseFees.getPayment_date().equals("")){
            mView.setError(2,"Empty Field Not Allowed");
            return false;
        }else if(caseFees.getAmount().equals("")){
            mView.setError(3,"Empty Field Not Allowed");
            return false;
        }else if(caseFees.getPayment_type().equals("")){
            mView.setError(4,"Empty Field Not Allowed");
            return false;
        }
        return true;
    }

    @Override
    public void saveCaseFees(CaseFees caseFees) {
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
        apiClient.saveCaseFees(caseFees).enqueue(new Callback<CaseFees>() {
            @Override
            public void onResponse(Call<CaseFees> call, Response<CaseFees> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<CaseFees> call, Throwable t) {

            }
        });
    }
}
