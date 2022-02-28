package com.forbitbd.lawyersdiary.model;

import java.util.Date;

public class AppointmentResponse {

    private String _id;
    private Client client;
    private String purpose;
    private String remarks;
    private String lawyer_id;
    private Date appointment_date;

    public AppointmentResponse() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLawyer_id() {
        return lawyer_id;
    }

    public void setLawyer_id(String lawyer_id) {
        this.lawyer_id = lawyer_id;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }
}
