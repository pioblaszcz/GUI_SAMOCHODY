import Samochody.Samochod;

import java.util.ArrayList;
import java.util.List;

public class Cennik {
    private ArrayList<Cena> listaCen = new ArrayList<Cena>();
    public Cennik(){}

    public void dodaj(typ typ, String nazwa, double cenaAbonament, double cenaZaKm, double cenaZaKm2, int limit){
        listaCen.add(new Cena(typ, nazwa, cenaAbonament, cenaZaKm, cenaZaKm2, limit));
    }
    public static Cennik pobierzCennik(){
        return new Cennik();
    }
}
