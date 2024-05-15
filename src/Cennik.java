import Samochody.Samochod;

import java.util.ArrayList;
import java.util.List;

public class Cennik {

    private static Cennik instancja = null;
    protected ArrayList<Cena> listaCen = new ArrayList<Cena>();
    public Cennik(){}

    public void dodaj(typ typ, String nazwa, double cenaAbonament, double cenaZaKm, double cenaZaKm2, int limit){
        listaCen.add(new Cena(typ, nazwa, cenaAbonament, cenaZaKm, cenaZaKm2, limit));
    }

    public void dodaj(typ typ, String nazwa, double cenaZaKm, double cenaZaKm2, int limit){
        listaCen.add(new Cena(typ, nazwa, cenaZaKm, cenaZaKm2, limit));
    }

    public void dodaj(typ typ, String nazwa, double cenaZaKm){
        listaCen.add(new Cena(typ, nazwa, cenaZaKm));
    }

    public void dodaj(typ typ, int limit, String nazwa){
        listaCen.add(new Cena(typ,  limit, nazwa));
    }


    public static Cennik pobierzCennik(){
        if (instancja == null) {
            instancja = new Cennik();
        }
        return instancja;
    }
}
