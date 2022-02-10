package com.forbitbd.lawyersdiary.ui.addcasetype;

import com.forbitbd.lawyersdiary.model.CaseType;

public interface AddCaseTypeContract {

    interface Presenter{
        boolean validate(CaseType caseType);
        void saveCaseType(CaseType caseType);
    }

    interface View{
        void clearError();
        void setError(int fieldId,String message);
        void closeDialog(CaseType caseType);
    }

}
