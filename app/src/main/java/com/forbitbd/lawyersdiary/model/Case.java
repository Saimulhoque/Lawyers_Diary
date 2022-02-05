package com.forbitbd.lawyersdiary.model;

import java.util.Date;

public class Case {

    private String case_title;
    private String client_id;
    private String case_type;
    private String client_name;
    private String case_number;
    private String file_number;
    private String court_name;
    private String city;
    private String complainant_Defandant;
    private Date case_reg_date;
    private Date oppositon_party_name;
    private String oppositon_lawyer_name;
    private String case_fees;
    private Boolean case_status;
    private String lawyer_id;
    private String remarks;

    public Case() {

    }

    public String getCase_title() {
        return case_title;
    }

    public void setCase_title(String case_title) {
        this.case_title = case_title;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getCase_type() {
        return case_type;
    }

    public void setCase_type(String case_type) {
        this.case_type = case_type;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
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

    public String getCourt_name() {
        return court_name;
    }

    public void setCourt_name(String court_name) {
        this.court_name = court_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplainant_Defandant() {
        return complainant_Defandant;
    }

    public void setComplainant_Defandant(String complainant_Defandant) {
        this.complainant_Defandant = complainant_Defandant;
    }

    public Date getCase_reg_date() {
        return case_reg_date;
    }

    public void setCase_reg_date(Date case_reg_date) {
        this.case_reg_date = case_reg_date;
    }

    public Date getOppositon_party_name() {
        return oppositon_party_name;
    }

    public void setOppositon_party_name(Date oppositon_party_name) {
        this.oppositon_party_name = oppositon_party_name;
    }

    public String getOppositon_lawyer_name() {
        return oppositon_lawyer_name;
    }

    public void setOppositon_lawyer_name(String oppositon_lawyer_name) {
        this.oppositon_lawyer_name = oppositon_lawyer_name;
    }

    public String getCase_fees() {
        return case_fees;
    }

    public void setCase_fees(String case_fees) {
        this.case_fees = case_fees;
    }

    public Boolean getCase_status() {
        return case_status;
    }

    public void setCase_status(Boolean case_status) {
        this.case_status = case_status;
    }

    public String getLawyer_id() {
        return lawyer_id;
    }

    public void setLawyer_id(String lawyer_id) {
        this.lawyer_id = lawyer_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
