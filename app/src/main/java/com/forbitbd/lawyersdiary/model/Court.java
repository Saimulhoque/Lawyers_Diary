package com.forbitbd.lawyersdiary.model;

import java.io.Serializable;

public class Court implements Serializable {

    private String _id;
    private String name;
    private String city;
    private String lawyer_id;

    public Court() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}
