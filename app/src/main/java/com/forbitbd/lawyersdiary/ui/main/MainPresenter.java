package com.forbitbd.lawyersdiary.ui.main;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.Dashboard;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter{

    private MainContract.View mView;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getDashboard(String lawyer_id) {
        ApiClient client = ServiceGenerator.createService(ApiClient.class);

        client.getDashboard(lawyer_id)
                .enqueue(new Callback<Dashboard>() {
                    @Override
                    public void onResponse(Call<Dashboard> call, Response<Dashboard> response) {
                        if(response.isSuccessful()){
                            mView.initUI(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Dashboard> call, Throwable t) {

                    }
                });
    }
}
