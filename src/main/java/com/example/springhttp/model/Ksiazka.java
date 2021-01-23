package com.example.springhttp.model;

import com.example.springhttp.utils.IdGenerator;

public class Ksiazka {

    private long id;
    private String tytul;
    private String autor;
    private boolean wypozyczona = false;

    public Ksiazka(String tytul, String autor) {
        this.id = IdGenerator.nextId();
        this.tytul = tytul;
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public String getTytul() {
        return tytul;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isWypozyczona() {
        return wypozyczona;
    }

    public void setWypozyczona(boolean wypozyczona) {
        this.wypozyczona = wypozyczona;
    }
}
