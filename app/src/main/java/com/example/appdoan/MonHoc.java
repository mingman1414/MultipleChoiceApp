package com.example.appdoan;

import androidx.annotation.NonNull;

public class MonHoc {
    private int id;
    private String tenmonhoc;

    public MonHoc(int id, String tenmonhoc) {
        this.id = id;
        this.tenmonhoc = tenmonhoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenmonhoc() {
        return tenmonhoc;
    }

    public void setTenmonhoc(String tenmonhoc) {
        this.tenmonhoc = tenmonhoc;
    }

    @NonNull
    @Override
    public String toString() {
        return getTenmonhoc();
    }
}
