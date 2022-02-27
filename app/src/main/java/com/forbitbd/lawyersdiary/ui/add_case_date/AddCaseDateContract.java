package com.forbitbd.lawyersdiary.ui.add_case_date;

import com.forbitbd.lawyersdiary.model.CaseDate;

public interface AddCaseDateContract {

    interface Presenter{
        void saveCaseDate(CaseDate caseDate);
        boolean validate(CaseDate caseDate);
    }

    interface View{
        void clearError();
        void setError(int fieldId, String message);

        void addCaseDate(CaseDate caseDate);
    }
}
