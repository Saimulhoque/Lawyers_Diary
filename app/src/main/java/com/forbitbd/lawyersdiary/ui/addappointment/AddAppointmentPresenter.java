package com.forbitbd.lawyersdiary.ui.addappointment;

import android.util.Log;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.Appointment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAppointmentPresenter implements AddAppointmentContract.Presenter {

    private AddAppointmentContract.View mView;

    public AddAppointmentPresenter(AddAppointmentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(Appointment appointment) {
        mView.clearError();
        if(appointment.getClient().equals("")) {
            mView.setError(1, "Empty Field Not Allowed");
            return false;
        }
        else if (appointment.getAppointment_date().equals("")) {
            mView.setError(2, "Empty Field Not Allowed");
            return false;
        }
        else if (appointment.getPurpose().equals("")) {
            mView.setError(3, "Empty Field Not Allowed");
            return false;
        }
        return true;
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
        apiClient.saveAppointment(appointment).enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                if (response.isSuccessful()){
                    mView.addAppointment(response.body());
                }
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable t) {

            }
        });
    }
}

