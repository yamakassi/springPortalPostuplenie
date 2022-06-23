package com.example.portal.domain;

import java.sql.Date;

public class CertificateJSON {
    private Integer c_id;

    private String patient_name;

    private String text;

    private String type;

    private Date date_issue;

    private Integer valid_period;

    private boolean conclusion;

    private String doctor_name;

    private String specilization;

    private String doctor_phone_number;

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate_issue() {
        return date_issue;
    }

    public void setDate_issue(Date date_issue) {
        this.date_issue = date_issue;
    }

    public Integer getValid_period() {
        return valid_period;
    }

    public void setValid_period(Integer valid_period) {
        this.valid_period = valid_period;
    }

    public boolean isConclusion() {
        return conclusion;
    }

    public void setConclusion(boolean conclusion) {
        this.conclusion = conclusion;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getSpecilization() {
        return specilization;
    }

    public void setSpecilization(String specilization) {
        this.specilization = specilization;
    }

    public String getDoctor_phone_number() {
        return doctor_phone_number;
    }

    public void setDoctor_phone_number(String doctor_phone_number) {
        this.doctor_phone_number = doctor_phone_number;
    }


}