package com.forbitbd.lawyersdiary.model;

import java.io.Serializable;
import java.util.Date;

public class Case implements Serializable {

    private String _id;
    private String case_title;
    private String client;
    private String case_type;
    private String case_number;
    private String file_number;
    private String court;
    private String complainant_Defendant;
    private String city;
    private String lawyer_id;
    private Date case_reg_date;
    private String opposition_party_name;
    private String opposition_lawyer_name;
    private String opposition_lawyer_phone;
    private double case_fees;
    private String remarks;
    private boolean case_status;

    public Case() {

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCase_title() {
        return case_title;
    }

    public void setCase_title(String case_title) {
        this.case_title = case_title;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCase_type() {
        return case_type;
    }

    public void setCase_type(String case_type) {
        this.case_type = case_type;
    }

    public String getCase_number() {
        return case_number;
    }

    public void setCase_number(String case_number) {
        this.case_number = case_number;
    }

    public String getFile_number() {
        return file_number;
    }

    public void setFile_number(String file_number) {
        this.file_number = file_number;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getComplainant_Defendant() {
        return complainant_Defendant;
    }

    public void setComplainant_Defendant(String complainant_Defendant) {
        this.complainant_Defendant = complainant_Defendant;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLawyer_id() {
        return lawyer_id;
    }

    public void setLawyer_id(String lawyer_id) {
        this.lawyer_id = lawyer_id;
    }

    public Date getCase_reg_date() {
        return case_reg_date;
    }

    public void setCase_reg_date(Date case_reg_date) {
        this.case_reg_date = case_reg_date;
    }

    public String getOpposition_party_name() {
        return opposition_party_name;
    }

    public void setOpposition_party_name(String opposition_party_name) {
        this.opposition_party_name = opposition_party_name;
    }

    public String getOpposition_lawyer_name() {
        return opposition_lawyer_name;
    }

    public void setOpposition_lawyer_name(String opposition_lawyer_name) {
        this.opposition_lawyer_name = opposition_lawyer_name;
    }

    public String getOpposition_lawyer_phone() {
        return opposition_lawyer_phone;
    }

    public void setOpposition_lawyer_phone(String opposition_lawyer_phone) {
        this.opposition_lawyer_phone = opposition_lawyer_phone;
    }

    public double getCase_fees() {
        return case_fees;
    }

    public void setCase_fees(double case_fees) {
        this.case_fees = case_fees;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getCase_status() {
        return case_status;
    }

    public void setCase_status(Boolean case_status) {
        this.case_status = case_status;
    }

    @Override
    public String toString() {
        return this.case_title;
    }
}
