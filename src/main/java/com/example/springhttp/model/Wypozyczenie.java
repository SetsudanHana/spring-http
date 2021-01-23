package com.example.springhttp.model;

import java.util.Date;

public class Wypozyczenie {
    private Ksiazka ksiazka;
    private Date dataWypozyczenia;
    private Date dataOddania;

    public Wypozyczenie(Ksiazka ksiazka) {
        this.ksiazka = ksiazka;
        this.ksiazka.setWypozyczona(true);
        this.dataWypozyczenia = new Date();
    }

    public void oddaj() {
        this.dataOddania = new Date();
        ksiazka.setWypozyczona(false);
    }

    public Ksiazka getKsiazka() {
        return ksiazka;
    }

    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public Date getDataOddania() {
        return dataOddania;
    }
}
