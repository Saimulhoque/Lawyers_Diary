package com.forbitbd.lawyersdiary.ui.schedule;

public class SchedulePresenter implements ScheduleContract.Presenter{

    private ScheduleContract.View mView;

    public SchedulePresenter(ScheduleContract.View mView) {
        this.mView = mView;
    }
}
