package com.forbitbd.lawyersdiary.ui.addcase.second;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.Case;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondFragmentPresenter implements SecondFragmentContract.Presenter{

    private SecondFragmentContract.View mView;

    public SecondFragmentPresenter(SecondFragmentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(Case aCase) {
        mView.clearError();

        if(aCase.getOpposition_party_name().equals("")){
            mView.setError(1,"Empty Field Not Allowed");
            return false;
        }else if(aCase.getOpposition_lawyer_name().equals("")){
            mView.setError(2,"Empty Field Not Allowed");
            return false;
        }else if(aCase.getOpposition_lawyer_phone().equals("")){
            mView.setError(3,"Empty Field Not Allowed");
            return false;
        }else if(aCase.getCase_fees()<=0){
            mView.setError(4,"Case fee Should be Greater than Zero");
            return false;
        }

        return true;
    }

    @Override
    public void saveCaseToServer(Case aCase) {
        ApiClient client = ServiceGenerator.createService(ApiClient.class);

        client.saveCase(aCase)
                .enqueue(new Callback<Case>() {
                    @Override
                    public void onResponse(Call<Case> call, Response<Case> response) {
                        if(response.isSuccessful()){
                            mView.finishActivity(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Case> call, Throwable t) {

                    }
                });

    }
}
