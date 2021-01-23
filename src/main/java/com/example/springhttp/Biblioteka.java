package com.example.springhttp;

import com.example.springhttp.exception.ConflictException;
import com.example.springhttp.exception.ForbiddenException;
import com.example.springhttp.exception.NotFoundException;
import com.example.springhttp.exception.TooManyRequestsException;
import com.example.springhttp.model.KartaBiblioteczna;
import com.example.springhttp.model.Ksiazka;
import com.example.springhttp.model.Wypozyczenie;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Biblioteka {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private Map<Long, KartaBiblioteczna> kartaBibliotecznaMap = new HashMap<>();
    private Map<Long, Ksiazka> ksiazkaMap = new HashMap<>();

    public List<Ksiazka> ksiazki() {
        return new ArrayList<>(ksiazkaMap.values());
    }

    public Ksiazka ksiazka(long id) {
        Ksiazka ksiazka = ksiazkaMap.get(id);
        if(ksiazka == null) {
            throw new NotFoundException();
        }
        return ksiazka;
    }

    public List<KartaBiblioteczna> kartaBiblioteczne() {
        return new ArrayList<>(kartaBibliotecznaMap.values());
    }

    public KartaBiblioteczna kartaBiblioteczna(long id) {
        KartaBiblioteczna kartaBiblioteczna = kartaBibliotecznaMap.get(id);
        if(kartaBiblioteczna == null) {
            throw new NotFoundException();
        }
        return kartaBiblioteczna;
    }

    public Ksiazka dodajKsiazka(String tytul, String autor) {
        Ksiazka ksiazka = new Ksiazka(tytul, autor);
        ksiazkaMap.put(ksiazka.getId(), ksiazka);
        return ksiazka;
    }

    public KartaBiblioteczna dodajKartaBiblioteczna(String imie, String nazwisko, String dataUrodzenia) throws ParseException {
        KartaBiblioteczna kartaBiblioteczna = new KartaBiblioteczna(imie, nazwisko, DATE_FORMAT.parse(dataUrodzenia));
        kartaBibliotecznaMap.put(kartaBiblioteczna.getId(), kartaBiblioteczna);
        return kartaBiblioteczna;
    }

    public KartaBiblioteczna wypozycz(long kartaBibliotecznaId, long ksiazkaId) {
        KartaBiblioteczna kartaBiblioteczna = kartaBiblioteczna(kartaBibliotecznaId);
        int wypozyczonych = 0;
        for(Wypozyczenie wypozyczenie : kartaBiblioteczna.getWypozyczenia()) {
            if(wypozyczenie.getDataOddania() == null) {
                ++wypozyczonych;
            }
        }

        if(wypozyczonych > 2) {
            throw new TooManyRequestsException();
        }

        Ksiazka ksiazka = ksiazka(ksiazkaId);
        if(ksiazka.isWypozyczona()) {
            throw new ConflictException();
        }

        kartaBiblioteczna.wypozycz(ksiazka);
        return kartaBiblioteczna;
    }

    public KartaBiblioteczna oddaj(long kartaBibliotecznaId, long ksiazkaId) {
        KartaBiblioteczna kartaBiblioteczna = kartaBiblioteczna(kartaBibliotecznaId);
        for(Wypozyczenie wypozyczenie : kartaBiblioteczna.getWypozyczenia()) {
            if(wypozyczenie.getKsiazka().getId() == ksiazkaId && wypozyczenie.getDataOddania() == null) {
                wypozyczenie.oddaj();
                return kartaBiblioteczna;
            }
        }
        throw new NotFoundException();
    }

    public void usunKartę(long kartaBibliotecznaId) {
        KartaBiblioteczna kartaBiblioteczna = kartaBiblioteczna(kartaBibliotecznaId);
        for(Wypozyczenie wypozyczenie : kartaBiblioteczna.getWypozyczenia()) {
            if (wypozyczenie.getDataOddania() == null) {
                throw new ForbiddenException();
            }
        }
        kartaBibliotecznaMap.remove(kartaBibliotecznaId);
    }
}
