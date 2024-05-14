import Samochody.Samochod;

public class Klient {

    private String nazwa;
    private int kwota;
    private boolean abonament;
    private ListaZyczen listaZyczen = new ListaZyczen();
    private Koszyk koszyk = new Koszyk();


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
    public Koszyk pobierzKoszyk(){
        return this.koszyk;
    }
}
