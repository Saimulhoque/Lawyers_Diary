package com.forbitbd.lawyersdiary.ui.addcase.first;

import com.forbitbd.lawyersdiary.model.Case;

public interface FirstFragmentContract {

    interface Presenter{
        boolean validate(Case ca_se);
        void saveCase(Case ca_se);

    }

    interface View{
        void clearError();
        void setError(int fieldId, String message);
        void saveCaseInActivity(Case ca_se);
    }

}
