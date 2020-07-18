package org.edwith.webbe.cardmanager.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BusinessCard {
    private String name;
    private String phone;
    private String companyName;
    private Date createDate;

    public BusinessCard(String name, String phone, String companyName) {
        this.name = name;
        this.phone = phone;
        this.companyName = companyName;
        this.createDate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
    	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
    	String formatteddate = sdf.format(createDate);
        return "BusinessCard{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", companyName='" + companyName + '\'' +
                ", createDate=" + formatteddate +
                '}';
    }
}
