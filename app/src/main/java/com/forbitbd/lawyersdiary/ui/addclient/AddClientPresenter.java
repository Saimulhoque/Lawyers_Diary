package com.forbitbd.lawyersdiary.ui.addclient;

import android.util.Log;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClientPresenter implements AddClientContract.Presenter{

    private AddClientContract.View mView;

    public AddClientPresenter(AddClientContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(Client client) {
        mView.clearError();
        if(client.getName().equals("")){
            mView.setError(1,"Empty Field Not Allowed");
            return false;
        }else if (client.getPhone_one().equals("")){
            mView.setError(2,"Empty Field Not Allowed");
        }else if (client.getPhone_two().equals("")){
            mView.setError(3,"Empty Field Not Allowed");
        }else if (client.getAddress().equals("")){
            mView.setError(4,"Empty Field Not Allowed");
        }else if (client.getDate_of_birth().equals("")){
            mView.setError(5,"Empty Field Not Allowed");
        }
        return true;
    }

    @Override
    public void saveClient(Client client) {

        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
        apiClient.postClient(client).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Log.d("KKKKKK", "onResponse: Successful!");
                if(response.isSuccessful()){
                    mView.closeDialog(response.body());

                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.d("KKKKKK", t.getMessage());
            }
        });
    }
}
