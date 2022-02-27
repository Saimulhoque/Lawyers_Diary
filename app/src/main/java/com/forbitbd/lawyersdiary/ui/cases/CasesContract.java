package com.forbitbd.lawyersdiary.ui.cases;

import com.forbitbd.lawyersdiary.model.Case;

import java.util.List;

public interface CasesContract {

    interface Presenter{

        void getCases(String lawyerId);
    }
    interface View{

        void addCase(List<Case> cases);
    }
}
