package com.forbitbd.lawyersdiary.ui.main;

import com.forbitbd.lawyersdiary.model.Dashboard;

public interface Communicator {
    void logOutFromApp();
    void startAddClientDialog();
    void startCalenderActivity();
    void startEvidenceActivity();
    void startAppointmentActivity();
    void startCasesActivity();
    void startClientActivity();
    void startAddCaseTypeDialog();
    void startAddCourtDialog();
    void startAddCaseActivity();
    void startAddCaseDateActivity();
    void startAddCaseFeesActivity();

    Dashboard getDashboard();

    int getTotalCases();
    int getActiveCases();
    int getClosedCases();
    int getNumberOfClients();
    int getNumberOfCaseTypes();
    void startLawActivity();
}
