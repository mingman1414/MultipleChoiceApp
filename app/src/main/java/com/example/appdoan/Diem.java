package com.example.appdoan;

public class Diem {
    private int id;
    private String ten;
    private int diem;

    public Diem(int id, String ten, int diem) {
        this.id = id;
        this.ten = ten;
        this.diem = diem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
}
