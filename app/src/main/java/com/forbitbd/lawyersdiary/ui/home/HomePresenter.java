package com.forbitbd.lawyersdiary.ui.home;

import android.util.Log;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getDashboardInfo(String lawyerId) {
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
//        apiClient.getDashboard(lawyerId).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    Log.d("KKKKKK", "onResponse: " );
//                    mView.addDashboard(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.d("KKKKKK", "onResponse: " + t.getMessage());
//            }
//        });
    }

    @Override
    public void updateUI() {
        mView.updateUI();
    }


}
