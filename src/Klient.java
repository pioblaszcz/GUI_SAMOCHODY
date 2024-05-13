import Samochody.Samochod;

public class Klient {

    private String nazwa;
    private int kwota;
    private boolean abonament;
    private ListaZyczen listaZyczen;


    public Klient(String nazwa, int kwota, boolean abonament){
        this.nazwa = nazwa;
        this.kwota = kwota;
        this.abonament = abonament;
    }

    public void dodaj(Samochod samochod){
        listaZyczen.dodajSamochod(samochod);
    }

    public ListaZyczen pobierzListeZyczen(){
        return this.listaZyczen;
    }
}
