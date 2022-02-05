package com.forbitbd.lawyersdiary.model;

import java.io.Serializable;
import java.util.List;

public class Dashboard implements Serializable {

    private List<Court> courts;
    private List<Client> clients;
    private List<CaseType> caseTypes;

    public Dashboard() {
    }

    public List<Court> getCourts() {
        return courts;
    }

    public void setCourts(List<Court> courts) {
        this.courts = courts;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<CaseType> getCaseTypes() {
        return caseTypes;
    }

    public void setCaseTypes(List<CaseType> caseTypes) {
        this.caseTypes = caseTypes;
    }
}
