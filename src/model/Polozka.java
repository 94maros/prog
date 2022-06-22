package model;
public class Polozka {
    private String meno;
    private String priezvisko;
    private String adresa;
    private String mesto;
    private String stat;
    private String telC;

    public String getMeno() {
        return meno;
    }
    public void setMeno(String meno) {
        this.meno = meno;
    }


    public String getPriezvisko() {
        return priezvisko;
    }
    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getAdresa() {
        return adresa;
    }
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMesto(String text) {
        return mesto;
    }
    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getStat() {
        return stat;
    }
    public void setStat(Object stat) {
        this.stat = stat.toString();
    }

    public String getTelC() {
        return telC;
    }
    public void setTelC(String telC) {
        this.telC = telC;
    }

    @Override
    public String toString() {
        return "Meno:" + meno +
                ", priezvisko:" + priezvisko +
                ", ulica:" + adresa +
                ", mesto:" + mesto +
                ", štát:" + stat +
                ", telefónne číslo:" + telC;
    }
}