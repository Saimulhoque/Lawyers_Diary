package com.forbitbd.lawyersdiary.model;

import java.io.Serializable;

public class Settings implements Serializable {

    private int option_icon;
    private String option_title;


    public Settings(int option_icon, String option_title) {
        this.option_icon = option_icon;
        this.option_title = option_title;
    }

    public int getOption_icon() {
        return option_icon;
    }

    public void setOption_icon(int option_icon) {
        this.option_icon = option_icon;
    }

    public String getOption_title() {
        return option_title;
    }

    public void setOption_title(String option_title) {
        this.option_title = option_title;
    }
}
