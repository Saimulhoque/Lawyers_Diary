package com.forbitbd.lawyersdiary.model;

import java.io.Serializable;

public class CaseType implements Serializable {

    private String _id;
    private String case_type;
    private String lawyer_id;

    public CaseType() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCase_type() {
        return case_type;
    }

    public void setCase_type(String case_type) {
        this.case_type = case_type;
    }

    public String getLawyer_id() {
        return lawyer_id;
    }

    public void setLawyer_id(String lawyer_id) {
        this.lawyer_id = lawyer_id;
    }

    @Override
    public String toString() {
        return case_type;
    }
}
