package com.forbitbd.lawyersdiary.ui.clients;

import android.util.Log;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsPresenter implements ClientsContract.Presenter{

    private ClientsContract.View mView;

    public ClientsPresenter(ClientsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getClients(String lawyerId) {
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
        apiClient.getAllClients(lawyerId).enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful()){
                    mView.responseClient(response.body());
                    Log.d("JJJJJJ", "onResponse: "+lawyerId);
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.d("JJJJJJ", "onResponse: "+t.getMessage());
            }
        });
    }
}