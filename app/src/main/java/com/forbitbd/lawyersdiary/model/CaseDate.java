package com.forbitbd.lawyersdiary.model;

import java.util.Date;

public class CaseDate {

    private String _id;
    private String case_id;
    private String judge_name;
    private String court;
    private String documents_required;
    private Date next_date;

    public CaseDate() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCase_id() {
        return case_id;
    }

    public void setCase_id(String case_id) {
        this.case_id = case_id;
    }

    public String getJudge_name() {
        return judge_name;
    }

    public void setJudge_name(String judge_name) {
        this.judge_name = judge_name;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getDocuments_required() {
        return documents_required;
    }

    public void setDocuments_required(String documents_required) {
        this.documents_required = documents_required;
    }

    public Date getNext_date() {
        return next_date;
    }

    public void setNext_date(Date next_date) {
        this.next_date = next_date;
    }
}
