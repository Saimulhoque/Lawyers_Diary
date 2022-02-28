package com.forbitbd.lawyersdiary.ui.appointment;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.AppointmentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentPresenter implements AppointmentContract.Presenter{

    private AppointmentContract.View mView;

    public AppointmentPresenter(AppointmentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getAppointments(String lawyerId) {
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
        apiClient.getAllAppointment(lawyerId).enqueue(new Callback<List<AppointmentResponse>>() {
            @Override
            public void onResponse(Call<List<AppointmentResponse>> call, Response<List<AppointmentResponse>> response) {
                if (response.isSuccessful()){
                    mView.addAppointments(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<AppointmentResponse>> call, Throwable t) {

            }
        });
    }
}
