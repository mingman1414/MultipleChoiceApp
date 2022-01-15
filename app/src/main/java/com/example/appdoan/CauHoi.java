package com.example.appdoan;

public class CauHoi {
    private int id;
    private String cauhoi;
    private String dapana;
    private String dapanb;
    private String dapanc;
    private String dapand;
    private int dapandung;
    private int monhoc_id;

    public CauHoi(int id, String cauhoi, String dapana, String dapanb, String dapanc, String dapand, int dapandung, int monhoc_id) {
        this.id = id;
        this.cauhoi = cauhoi;
        this.dapana = dapana;
        this.dapanb = dapanb;
        this.dapanc = dapanc;
        this.dapand = dapand;
        this.dapandung = dapandung;
        this.monhoc_id = monhoc_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getDapana() {
        return dapana;
    }

    public void setDapana(String dapana) {
        this.dapana = dapana;
    }

    public String getDapanb() {
        return dapanb;
    }

    public void setDapanb(String dapanb) {
        this.dapanb = dapanb;
    }

    public String getDapanc() {
        return dapanc;
    }

    public void setDapanc(String dapanc) {
        this.dapanc = dapanc;
    }

    public String getDapand() {
        return dapand;
    }

    public void setDapand(String dapand) {
        this.dapand = dapand;
    }

    public int getDapandung() {
        return dapandung;
    }

    public void setDapandung(int dapandung) {
        this.dapandung = dapandung;
    }

    public int getMonhoc_id() {
        return monhoc_id;
    }

    public void setMonhoc_id(int monhoc_id) {
        this.monhoc_id = monhoc_id;
    }
}
