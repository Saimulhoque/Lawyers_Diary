package com.forbitbd.lawyersdiary.ui.addcourt;

import com.forbitbd.lawyersdiary.model.Court;

public interface AddCourtContract {

    interface Presenter{
        boolean validate(Court court);
        void saveCourtName(Court court);
    }

    interface View{
        void clearError();
        void setError(int fieldId, String message);
        void closeDialog(Court body);
    }
}
