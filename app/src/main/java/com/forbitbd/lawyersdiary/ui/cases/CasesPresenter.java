package com.forbitbd.lawyersdiary.ui.cases;

import android.util.Log;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.Case;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CasesPresenter implements CasesContract.Presenter{

    private CasesContract.View mView;

    public CasesPresenter(CasesContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getCases(String lawyerId) {
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
        apiClient.getAllCases(lawyerId).enqueue(new Callback<List<Case>>() {
            @Override
            public void onResponse(Call<List<Case>> call, Response<List<Case>> response) {
                if (response.isSuccessful()){
                    Log.d("JJJJJJJ", "onResponse: Case Found");
                    mView.addCase(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Case>> call, Throwable t) {

            }
        });
    }


}
