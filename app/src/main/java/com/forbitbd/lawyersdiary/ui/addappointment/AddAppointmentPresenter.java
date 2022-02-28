package com.forbitbd.lawyersdiary.ui.addappointment;

import android.util.Log;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;
import com.forbitbd.lawyersdiary.model.AppointmentRequest;
import com.forbitbd.lawyersdiary.model.AppointmentResponse;
import com.forbitbd.lawyersdiary.model.OthersAppointmentRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAppointmentPresenter implements AddAppointmentContract.Presenter {

    private AddAppointmentContract.View mView;

    public AddAppointmentPresenter(AddAppointmentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(AppointmentRequest appointmentRequest) {
        mView.clearError();
        if (appointmentRequest.getClient() == null) {
            mView.setError(1, "Empty Field Not Allowed");
            return false;
        } else if (appointmentRequest.getAppointment_date() == null || appointmentRequest.getAppointment_date().equals("")) {
            mView.setError(2, "Please Set a Proper Appointment Date");
            return false;
        } else if (appointmentRequest.getPurpose().equals("")) {
            mView.setError(3, "Empty Field Not Allowed");
            return false;
        }
        return true;
    }

    @Override
    public boolean validateOthersAppointment(OthersAppointmentRequest appointmentRequest) {
        mView.clearError();
        if (appointmentRequest.getName() == null || appointmentRequest.getName().equals("")) {
            mView.setError(4, "Empty Field Not Allowed");
            return false;
        } else if (appointmentRequest.getContact() == null || appointmentRequest.getContact().equals("")) {
            mView.setError(5, "Please Set a Proper Contact Number");
            return false;
        } else if (appointmentRequest.getAppointment_date() == null || appointmentRequest.getAppointment_date().equals("")) {
            mView.setError(2, "Please Set a Proper Appointment Date");
            return false;
        } else if (appointmentRequest.getPurpose().equals("")) {
            mView.setError(3, "Empty Field Not Allowed");
            return false;
        }
        return true;
    }

    @Override
    public void saveAppointment(AppointmentRequest appointmentRequest) {
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
        apiClient.saveAppointment(appointmentRequest).enqueue(new Callback<AppointmentResponse>() {
            @Override
            public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {
                if (response.isSuccessful()) {
                    mView.closeDialog(response.body());
                }
            }

            @Override
            public void onFailure(Call<AppointmentResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void saveOthersAppointment(OthersAppointmentRequest appointmentRequest) {
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);

        apiClient.saveOthersAppointment(appointmentRequest)
                .enqueue(new Callback<AppointmentResponse>() {
                    @Override
                    public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {
                        if (response.isSuccessful()) {
                            mView.closeDialog(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<AppointmentResponse> call, Throwable t) {

                    }
                });
    }
}

