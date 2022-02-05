package com.forbitbd.lawyersdiary.ui.appointment;

public class AppointmentPresenter implements AppointmentContract.Presenter{

    private AppointmentContract.View mView;

    public AppointmentPresenter(AppointmentContract.View mView) {
        this.mView = mView;
    }
}
