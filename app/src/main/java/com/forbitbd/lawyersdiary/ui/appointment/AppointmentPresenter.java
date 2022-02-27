package com.forbitbd.lawyersdiary.ui.appointment;

import com.forbitbd.lawyersdiary.api.ApiClient;
import com.forbitbd.lawyersdiary.api.ServiceGenerator;

public class AppointmentPresenter implements AppointmentContract.Presenter{

    private AppointmentContract.View mView;

    public AppointmentPresenter(AppointmentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getAppointments(String lawyerId) {
        ApiClient apiClient = ServiceGenerator.createService(ApiClient.class);
    }
}
