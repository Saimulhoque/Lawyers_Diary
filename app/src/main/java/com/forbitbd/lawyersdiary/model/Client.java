package com.forbitbd.lawyersdiary.model;

import java.io.Serializable;
import java.util.Date;

public class Client implements Serializable {

    private String _id;
    private String name;
    private String phone_one;
    private String phone_two;
    private String address;
    private String email;
    private Date date_of_birth;
    private Date reg_date;
    private String lawyer_id;

    public Client() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_one() {
        return phone_one;
    }

    public void setPhone_one(String phone_one) {
        this.phone_one = phone_one;
    }

    public String getPhone_two() {
        return phone_two;
    }

    public void setPhone_two(String phone_two) {
        this.phone_two = phone_two;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public String getLawyer_id() {
        return lawyer_id;
    }

    public void setLawyer_id(String lawyer_id) {
        this.lawyer_id = lawyer_id;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return name;
    }
}
