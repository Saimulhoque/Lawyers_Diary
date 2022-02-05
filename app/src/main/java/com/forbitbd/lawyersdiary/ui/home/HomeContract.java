package com.forbitbd.lawyersdiary.ui.home;

public interface HomeContract {

    interface Presenter{
        void getDashboardInfo(String lawyerId);
    }

    interface View{
        void addDashboard(String dashboard);
    }

}
