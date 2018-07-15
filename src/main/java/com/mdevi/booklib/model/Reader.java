package com.mdevi.booklib.model;

import java.sql.Date;

public class Reader {
    private int id;
    private String name;
    private boolean gender;
    private Date dob;
    private int point;

    public Reader() {
    }

    public Reader(int id, String name, boolean gender, Date dob, int point) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
