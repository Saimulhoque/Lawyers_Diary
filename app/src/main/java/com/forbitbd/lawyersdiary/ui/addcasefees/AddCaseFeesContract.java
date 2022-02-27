package com.forbitbd.lawyersdiary.ui.addcasefees;

import com.forbitbd.lawyersdiary.model.CaseFees;

public interface AddCaseFeesContract {

    interface Presenter{

        boolean validate(CaseFees caseFees);

        void saveCaseFees(CaseFees caseFees);
    }

    interface View{

        void clearError();

        void setError(int fieldId, String message);
    }
}
