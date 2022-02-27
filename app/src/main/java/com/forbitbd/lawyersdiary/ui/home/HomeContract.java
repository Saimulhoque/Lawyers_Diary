package com.forbitbd.lawyersdiary.ui.home;

public interface HomeContract {

    interface Presenter{
        void getDashboardInfo(String lawyerId);
        void updateUI();
    }

    interface View{
        void addDashboard(String dashboard);
        void updateUI();
    }

}
