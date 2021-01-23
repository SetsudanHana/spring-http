package com.example.springhttp.model;

import com.example.springhttp.utils.IdGenerator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KartaBiblioteczna {
    private long id;
    private String imie;
    private String nazwisko;
    private Date dataUrodzenia;

    public KartaBiblioteczna(String imie, String nazwisko, Date dataUrodzenia) {
        this.id = IdGenerator.nextId();
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
    }

    private List<Wypozyczenie> wypozyczenia = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void wypozycz(Ksiazka ksiazka) {
        wypozyczenia.add(new Wypozyczenie(ksiazka));
    }

    public List<Wypozyczenie> getWypozyczenia() {
        return wypozyczenia;
    }
}
