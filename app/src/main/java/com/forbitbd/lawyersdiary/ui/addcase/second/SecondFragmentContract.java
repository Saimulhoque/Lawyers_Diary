package com.forbitbd.lawyersdiary.ui.addcase.second;

import com.forbitbd.lawyersdiary.model.Case;

public interface SecondFragmentContract {

    interface Presenter{
        boolean validate(Case aCase);
        void saveCaseToServer(Case aCase);
    }

    interface View{
        void clearError();
        void finishActivity(Case aCase);
        void setError(int fieldId, String message);
    }
}
