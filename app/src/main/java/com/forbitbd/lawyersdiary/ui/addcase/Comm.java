package com.forbitbd.lawyersdiary.ui.addcase;

import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.Dashboard;

public interface Comm {
    Dashboard getDashBoard();
    String getCaseTypeObjectId(String casetypeId);
    String getCourtObjectId(String courtName);
    String getClientObjectId(String clientName);

    void saveCase(Case ca_se);
    void finishActivity(Case aCase);
    Case getCase();
}
