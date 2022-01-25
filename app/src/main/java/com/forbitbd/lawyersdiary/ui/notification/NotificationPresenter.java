package com.forbitbd.lawyersdiary.ui.notification;

public class NotificationPresenter implements NotificationContract.Presenter{

    private NotificationContract.View mView;

    public NotificationPresenter(NotificationContract.View mView) {
        this.mView = mView;
    }
}
