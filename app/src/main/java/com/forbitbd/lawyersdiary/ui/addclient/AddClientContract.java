package com.forbitbd.lawyersdiary.ui.addclient;

import com.forbitbd.lawyersdiary.model.Client;

public interface AddClientContract {

    interface Presenter{

        boolean validate(Client client);
        void saveClient(Client client);
    }

    interface View{

        void clearError();
        void setError(int fieldId,String message);
        void closeDialog(Client client);
    }
}
