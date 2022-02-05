package com.forbitbd.lawyersdiary.ui.clients;

import com.forbitbd.lawyersdiary.model.Client;

import java.util.List;

public interface ClientsContract {

    interface Presenter{
        void getClients(String lawyerId);
    }

    interface View{
        void responseClient(List<Client> client);
    }
}
