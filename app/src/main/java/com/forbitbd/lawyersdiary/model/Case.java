package com.forbitbd.lawyersdiary.model;

public class Case {

    private String case_title, case_type, court_name, starting_date, judge_name, party, opp_party_name, opp_lawyer;

    public Case(String case_title, String case_type, String court_name, String starting_date, String judge_name, String party, String opp_party_name, String opp_lawyer) {
        this.case_title = case_title;
        this.case_type = case_type;
        this.court_name = court_name;
        this.starting_date = starting_date;
        this.judge_name = judge_name;
        this.party = party;
        this.opp_party_name = opp_party_name;
        this.opp_lawyer = opp_lawyer;
    }

    public String getCase_title() {
        return case_title;
    }

    public void setCase_title(String case_title) {
        this.case_title = case_title;
    }

    public String getCase_type() {
        return case_type;
    }

    public void setCase_type(String case_type) {
        this.case_type = case_type;
    }

    public String getCourt_name() {
        return court_name;
    }

    public void setCourt_name(String court_name) {
        this.court_name = court_name;
    }

    public String getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(String starting_date) {
        this.starting_date = starting_date;
    }

    public String getJudge_name() {
        return judge_name;
    }

    public void setJudge_name(String judge_name) {
        this.judge_name = judge_name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getOpp_party_name() {
        return opp_party_name;
    }

    public void setOpp_party_name(String opp_party_name) {
        this.opp_party_name = opp_party_name;
    }

    public String getOpp_lawyer() {
        return opp_lawyer;
    }

    public void setOpp_lawyer(String opp_lawyer) {
        this.opp_lawyer = opp_lawyer;
    }
}
