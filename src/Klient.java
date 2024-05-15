import Samochody.Samochod;

public class Klient {

    private String imie;
    private int kwota;
    private boolean abonament;
    protected ListaZyczen listaZyczen;
    private Koszyk koszyk = new Koszyk();


    public Klient(String imie, int kwota, boolean abonament){
        this.imie = imie;
        this.kwota = kwota;
        this.abonament = abonament;
        this.listaZyczen = new ListaZyczen(imie, abonament);
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
    public void przepakuj(){

        for(Samochod samochod : this.listaZyczen){
            boolean znalezionoCene = false;

            for(Cena c : Cennik.pobierzCennik().listaCen){
                if(c.typ.name().equals("DARMO") && c.typ.name().equals(samochod.getType()) && this.abonament == true){
                    znalezionoCene = true;
                }
                else if (c.typ.name() != "DARMO" && c.typ.name().equals(samochod.getType()) && c.nazwa.equals(samochod.getMarka())){
                    znalezionoCene = true;
                }
            }

            if(znalezionoCene) System.out.println("Znaleziono");
        }
    }

    public void zaplac(){

    }
}
