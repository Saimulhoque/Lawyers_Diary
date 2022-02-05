package com.forbitbd.lawyersdiary.ui.main;

import com.forbitbd.lawyersdiary.model.Dashboard;

public interface MainContract {

    interface Presenter{
        void getDashboard(String lawyer_id);
    }

    interface View{
        void initUI(Dashboard dashboard);
    }
}
