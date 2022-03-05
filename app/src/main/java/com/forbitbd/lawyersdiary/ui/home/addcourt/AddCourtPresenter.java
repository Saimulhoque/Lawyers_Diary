package com.forbitbd.lawyersdiary.ui.home.addcourt;

import android.util.Log;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.Court;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCourtPresenter implements AddCourtContract.Presenter{

    private AddCourtContract.View mView;

    public AddCourtPresenter(AddCourtContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(Court court) {
        mView.clearError();
        if(court.getName().equals("")){
            mView.setError(1,"Empty Field Not Allowed");
            return false;
        }else if (court.getCity().equals("")){
            mView.setError(2,"Empty Field Not Allowed");
        }
        return true;
    }

    @Override
    public void saveCourtName(Court court) {
        ApiClient client = ServiceGenerator.createService(ApiClient.class);
        client.postCourt(court).enqueue(new Callback<Court>() {
            @Override
            public void onResponse(Call<Court> call, Response<Court> response) {
                if (response.isSuccessful()){
                    Log.d("HHHHHH", "onResponse: "+response.toString());
                    mView.closeDialog(response.body());
                }
            }

            @Override
            public void onFailure(Call<Court> call, Throwable t) {
                Log.d("HHHHHH", "onResponse: "+t.getMessage());
            }
        });
    }
}
